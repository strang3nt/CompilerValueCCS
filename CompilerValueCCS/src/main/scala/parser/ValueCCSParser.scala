package main.scala.parser

import org.antlr.v4.runtime._

import main.scala.ast._
import main.scala.ast.Aexpr._
import main.scala.ast.Aexpr.Factor._
import main.scala.ast.Bexpr._
import main.scala.ast.Bexpr.BoolOperator._
import main.scala.ast.Bexpr.BoolTerm._
import main.scala.ast.Bexpr.LogicOperator._
import main.scala.ast.Bexpr.UnLogicOperator._
import main.scala.ast.CommonAst._

import scala.jdk.CollectionConverters._
import main.scala.parser.CcsvpParser.SummationContext
import main.scala.parser.CcsvpParser.ProductContext
import main.scala.parser.CcsvpParser.LogicopContext
import main.scala.parser.CcsvpParser.BoolopContext

object ValueCCSParser extends CcsvpBaseVisitor[ValueCCS]:

  override def visitExpr(ctx: CcsvpParser.ExprContext): Expr =
    Expr(
      visitTerm(ctx.term(0)),
      ctx.summation.asScala
        .zip(ctx.term.asScala.tail)
        .map((x, y) => (visitSummation(x), visitTerm(y)))
        .toList
    )

  override def visitTerm(ctx: CcsvpParser.TermContext): Term =
    Term(
      visitFactor(ctx.factor(0)),
      ctx.product.asScala
        .zip(ctx.factor.asScala.tail)
        .map((x, y) => (visitProduct(x), visitFactor(y)))
        .toList
    )

  override def visitFactor(ctx: CcsvpParser.FactorContext): Factor =
    if (ctx.expr != null) then Parenthesis(visitExpr(ctx.expr))
    else if (ctx.IDENTIFIER != null) then ID(Variable(ctx.IDENTIFIER.getText))
    else // number
      NUMBER(Natural(Integer.parseInt(ctx.INTEGER.getText)))

  override def visitSummation(x: SummationContext): Summation =
    x.getText match
      case "+" => Add
      case "-" => Sub

  override def visitProduct(x: ProductContext): Product =
    x.getText match
      case "*"  => Mul
      case "\\" => Div

  override def visitBoolbinop(ctx: CcsvpParser.BoolbinopContext): BoolBinOp =
    BoolBinOp(
      visitBterm(ctx.bterm(0)),
      ctx.logicop.asScala
        .zip(ctx.bterm.asScala.tail)
        .map((x, y) => (visitLogicop(x), visitBterm(y)))
        .toList
    )

  override def visitBterm(ctx: CcsvpParser.BtermContext): BoolTerm =
    if (ctx.NOT != null) then UnOp(Neq, visitBoolbinop(ctx.boolbinop))
    else if (ctx.exprbinop != null) then BoolExpr(visitExprbinop(ctx.exprbinop))
    else // lbracket boolbinop rbracket
      ParBoolOp(visitBoolbinop(ctx.boolbinop))

  override def visitExprbinop(ctx: CcsvpParser.ExprbinopContext): ExprBinOp =
    ExprBinOp(
      visitExpr(ctx.expr(0)),
      visitBoolop(ctx.boolop),
      visitExpr(ctx.expr(1))
    )

  override def visitLogicop(x: LogicopContext): LogicOperator =
    x.getText match
      case "&&" => Land
      case "||" => Lor

  override def visitBoolop(x: BoolopContext): BoolOperator =
    x.getText match
      case "<=" => Leq
      case "<"  => Le
      case ">=" => Geq
      case ">"  => Ge
      case "==" => Eq

  override def visitConstant(ctx: CcsvpParser.ConstantContext): Constant =
    Constant(
      ctx.IDENTIFIER(0).getText,
      if (ctx.IDENTIFIER.size > 1) then
        Some(ctx.IDENTIFIER.asScala.tail.map(x => Variable(x.getText)).toList)
      else None
    )

  override def visitTauch(ctx: CcsvpParser.TauchContext): ValueCCS =
    TauCh(visit(ctx.ccsvp))

  override def visitPar(ctx: CcsvpParser.ParContext): ValueCCS =
    Par(visit(ctx.ccsvp(0)), visit(ctx.ccsvp(1)))

  override def visitRestriction(ctx: CcsvpParser.RestrictionContext): ValueCCS =
    Restrict(
      visit(ctx.ccsvp),
      ctx.IDENTIFIER.asScala.map(x => Channel(x.getText)).toList
    )

  override def visitInputch(ctx: CcsvpParser.InputchContext): ValueCCS =
    InputCh(
      Channel(ctx.IDENTIFIER(0).getText),
      if (ctx.IDENTIFIER.size > 1) then
        Some(Variable(ctx.IDENTIFIER(1).getText))
      else None,
      visit(ctx.ccsvp)
    )

  override def visitParenthesis(ctx: CcsvpParser.ParenthesisContext): ValueCCS =
    visit(ctx.ccsvp)

  override def visitRedirection(ctx: CcsvpParser.RedirectionContext): ValueCCS =
    Redirection(
      visit(ctx.ccsvp),
      ctx.IDENTIFIER.asScala
        .zip(ctx.IDENTIFIER.asScala.tail)
        .zipWithIndex
        .filter((_, i) => i % 2 == 0)
        .map { case ((x, y), _) =>
          (Channel(x.getText()), Channel(y.getText()))
        }
        .toList
    )

  override def visitConst(ctx: CcsvpParser.ConstContext): ValueCCS =
    Const(
      ctx.IDENTIFIER.getText,
      if (ctx.expr.isEmpty) then None
      else Some(ctx.expr.asScala.map(x => visitExpr(x)).toList)
    )

  override def visitSum(ctx: CcsvpParser.SumContext): ValueCCS =
    Sum(ctx.ccsvp.asScala.map(x => visit(x)).toList)

  override def visitIfthen(ctx: CcsvpParser.IfthenContext): ValueCCS =
    IfThen(visitBoolbinop(ctx.boolbinop), visit(ctx.ccsvp))

  override def visitOutputch(ctx: CcsvpParser.OutputchContext): ValueCCS =
    OutputCh(
      Channel(ctx.IDENTIFIER.getText),
      if (ctx.expr.isEmpty) then None else Some(visitExpr(ctx.expr)),
      visit(ctx.ccsvp)
    )

  override def visitProgram(ctx: CcsvpParser.ProgramContext): ValueCCSProgram =
    ValueCCSProgram(visitConstant(ctx.constant), visit(ctx.ccsvp))
