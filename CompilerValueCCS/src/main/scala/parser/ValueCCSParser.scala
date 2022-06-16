package main.scala.parser

import cats.parse.{Numbers, Parser, Parser0}
import cats.parse.Rfc5234._
import cats.parse.Numbers._
import cats.parse.Parser.string
import main.scala.ast.CommonAst._
import main.scala.ast.ValueCCSAst._

import main.scala.ast.Bexpr._
import UnLogicOperator._
import LogicOperator._
import BoolOperator._
import Bexpr._
import BoolTerm._

import main.scala.ast.Aexpr._
import Aexpr._
import Factor._
import Term._
import Expr._
import ValueCCS._
import Natural._

object ValueCCSParser:


    def parse(input : String): Either[Parser.Error, ValueCCS] =
      valueCCS
        .parse(input.filterNot(_.isWhitespace))
        .map(_._2)

    private val separator = Parser.char('.').void
    private val lowerCaseString = Parser.charWhere(c => c.isLower && c.isLetter).rep.string
    private val op = Parser.char('(')
    private val cp = Parser.char(')')

    private val variable = lowerCaseString map (Variable(_))
    private val channel = lowerCaseString map (Channel(_))
    private val number = Parser
      .stringIn("1" :: "2" ::"3" :: "4" :: "5" :: Nil)
      .map{ 
        case "1" => One
        case "2" => Two
        case "3" => Three
        case "4" => Four
        case "5" => Five
      }

    private val addOrSub: Parser[Add.type | Sub.type] = Parser
      .stringIn("+" :: "-" :: Nil)
      .map {
        case "-" => Sub
        case "+" => Add
      }

    private val mulOrDiv: Parser[Mul.type | Div.type] = Parser
      .stringIn("\\" :: "*" :: Nil)
      .map {
        case "\\" => Div
        case "*" => Mul
      }

    private val unLogicOperator = Parser.stringIn("!" :: Nil).map{ case "!" => Neq }
    private val logicOperator = Parser
      .stringIn("&&" :: "||" :: Nil)
      .map {
        case "&&" => Land
        case "||" => Lor
      }
    private val boolOperator = Parser
      .stringIn("<" :: ">" :: "<=" :: ">=" :: "==" :: Nil)
      .map {
        case "<=" => Leq
        case "==" => Eq
        case "<" => Le
        case ">" => Ge
        case ">=" => Geq
      }


    private[parser] val aexpr: Parser[Aexpr] =
      lazy val parenthesis = (op.string *> expr <* cp.string).map (Parenthesis(_))
      lazy val number_ = number map (NUMBER(_))
      lazy val id = variable map (ID(_))
      lazy val factor: Parser[Factor] = Parser.defer(parenthesis | number_ | id)
      lazy val term: Parser[Term] =
        (factor ~ (mulOrDiv ~ factor).rep.?)
        .map { 
          case(f, Some(l)) => Multiplication(f, l.toList) 
          case(f, None) => Multiplication(f, List.empty)}
      lazy val expr: Parser[Expr] = 
        (term ~ (addOrSub ~ term).rep.?)
        .map{ 
          case(t, Some (l)) => Summation(t, l.toList) 
          case(t, None) => Summation(t, List.empty)}

      Parser.oneOf(expr.backtrack :: term.backtrack :: factor :: Nil).map{case a: (Expr | Term | Factor) => Aexpr(a)}


    private[parser] val bexpr: Parser[Bexpr] =
      lazy val parBoolOp: Parser[ParBoolOp] = (op.string *> boolBinOp <* cp.string).map(ParBoolOp(_))
      lazy val boolExpr: Parser[BoolExpr] = exprBinOp.map(BoolExpr(_))
      lazy val unOp: Parser[UnOp] = (unLogicOperator ~ boolBinOp).map((op, b) => UnOp(op, b))
      lazy val boolTerm: Parser[BoolTerm] = parBoolOp | boolExpr | unOp
      lazy val boolBinOp: Parser[BoolBinOp] = Parser
        .defer(boolTerm ~ (logicOperator ~ boolTerm).rep.?)
        .map {
          case (b, Some(l)) => BoolBinOp(b, l.toList)
          case (b, None) => BoolBinOp(b, List.empty)}
      lazy val exprBinOp: Parser[ExprBinOp] = (this.aexpr ~ boolOperator ~ this.aexpr).map{case((l, o), r) => ExprBinOp(l, o, r)}
      Parser.oneOf(boolBinOp.backtrack :: boolTerm.backtrack :: exprBinOp :: Nil).map{ case b: (BoolBinOp | BoolTerm | ExprBinOp) => Bexpr(b)}
    

    private[parser] val constant: Parser[Constant] =
      val constantName = (Parser.charWhere(_.isUpper) ~ alpha.rep0).string
      (constantName ~ (op *> aexpr.repSep(Parser.char(',')) <* cp).?)
        .map{case (name, list) => Constant (name, list.map { _.toList })}


    private[parser] lazy val inputCh = Parser
      .defer((channel ~ ( op *> variable <* cp).?) ~ (separator *> valueCCS).?)
      .map{case ((ch, v), proc) => InputCh (ch, v, proc)}


    private[parser] lazy val outputCh = Parser
      .defer((Parser.char('\'') *> channel) ~ (op *> aexpr <* cp).? ~ (separator *> valueCCS).?)
      .map { case ((ch, v), proc) => OutputCh (ch, v, proc) }


    private[parser] lazy val ifThen = Parser
      .defer((Parser.string("if") ~ op *> bexpr <* cp) ~ (Parser.string("then") *> valueCCS))
      .map(IfThen (_, _))


    private[parser] lazy val par = Parser
      .defer(valueCCS ~ (Parser.char('|').void *> valueCCS))
      .map(Par(_, _))


    private[parser] lazy val sum = Parser
      .defer(valueCCS.repSep(Parser.char('+')))
      .map{ x => Sum (x.toList) }


    private[parser] lazy val restrict = Parser
      .defer(valueCCS ~ ((Parser.char('\\') ~ Parser.char('{')) *> channel.repSep(Parser.char(',')) <* Parser.char('}')))
      .map{case (proc, chs) => Restrict (proc, chs.toList)}


    private[parser] lazy val valueCCS: Parser[ValueCCS] =
      Parser.oneOf(
        constant :: 
        inputCh :: 
        outputCh :: 
        ifThen :: 
        par :: 
        sum :: 
        restrict :: 
        Nil
      )
