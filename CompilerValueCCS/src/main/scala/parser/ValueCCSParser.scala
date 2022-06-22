package main.scala.parser

import scala.util.parsing.combinator.Parsers
import scala.util.parsing.combinator.PackratParsers

import scala.util.parsing.input.{ Reader, Position, NoPosition }

import main.scala.lexer.CCSToken
import CCSToken._

import main.scala.ast.Aexpr._
import Factor._  
import main.scala.ast.Naturals._
import main.scala.ast.ValueCCSAst._
import ValueCCS._
import main.scala.ast.CommonAst.Variable
import main.scala.ast.Bexpr._
import BoolTerm._
import UnLogicOperator._
import LogicOperator._
import BoolOperator._
import main.scala.ast.CommonAst.Tau
import main.scala.ast.CommonAst.Channel

object ValueCCSParser extends Parsers with PackratParsers:
  override type Elem = CCSToken

  class CCSTokenReader(tokens: Seq[CCSToken]) extends Reader[CCSToken] {
    override def first: CCSToken = tokens.head
    override def atEnd: Boolean = tokens.isEmpty
    override def pos: Position = tokens.headOption.map(_.pos).getOrElse(NoPosition)
    override def rest: Reader[CCSToken] = new CCSTokenReader(tokens.tail)
  }

  def apply[A](tokens: Seq[CCSToken], program: PackratParser[A]): Either[((Int, Int), String), A] = {
    val reader = new PackratReader(new CCSTokenReader(tokens))
    program(reader) match {
      case NoSuccess(msg, next) => Left(((next.pos.line, next.pos.column), msg))
      case Success(result, next) => Right(result)
      case err @ _ => throw new Exception(s"ValueCCSParser: Fatal: $err")
    }
  }
  
  lazy val program: PackratParser[ValueCCS] = phrase(valueCCS)

  lazy val aexpr: PackratParser[Aexpr] = 
    positioned {
      lazy val parenthesis = LBRACKET() ~> expr <~ RBRACKET() ^^ (Parenthesis(_))
      lazy val number = integer ^^ { case INTEGER(nat) => NUMBER(Natural(nat))}
      lazy val id = identifier ^^ { case IDENTIFIER(name) => ID(Variable(name))}
      lazy val factor = number | parenthesis | id
      lazy val term = factor ~ rep((DIV() | MUL()) ~ factor).? ^^ {
        case f ~ None => Term(f, List.empty)
        case f ~ Some(l) => Term(f, l.map{
          case DIV() ~ x => (Div, x)
          case MUL() ~ x => (Mul, x)
        })
        }

      lazy val expr: Parser[Expr] = term ~ rep( (SUM() | SUB()) ~ term).? ^^ {
        case t ~ None => Expr(t, List.empty)
        case t ~ Some(l) => Expr(t, l.map{
          case SUM() ~ x => (Add, x)
          case SUB() ~ x => (Sub, x)})}

      expr | factor | term
    }

  lazy val bexpr: PackratParser[Bexpr]=
    positioned {
      lazy val parBoolOp = LBRACKET() ~> boolBinOp <~ RBRACKET() ^^ (ParBoolOp(_))
      lazy val boolExpr = exprBinOp ^^ (BoolExpr(_))
      lazy val unOp = unLogicOperator ~ boolBinOp ^^ { case op ~ b => UnOp(op, b)}
      lazy val boolTerm: Parser[BoolTerm] = parBoolOp | boolExpr | unOp
      lazy val boolBinOp = boolTerm ~ rep(logicOperator ~ boolTerm).? ^^ {
        case b ~ None => BoolBinOp(b, List.empty)
        case b ~ Some(l) => BoolBinOp(b, l.map{ case op ~ b => (op, b)})
      }
      lazy val exprBinOp = aexpr ~ boolOperator ~ aexpr ^^ { case l ~ o ~ r => ExprBinOp(l, o, r)}
      boolBinOp | boolTerm | exprBinOp
    }

  private lazy val unLogicOperator: PackratParser[UnLogicOperator] = 
    positioned {
      accept("Unary logic operator", { case NOT() => Neq})
    }

  private lazy val logicOperator: PackratParser[LogicOperator] =
    positioned {
      accept("Logic operator", {
        case OR() => Lor 
        case AND() => Land
      })
    }

  private lazy val boolOperator: PackratParser[BoolOperator] =
    positioned {
      accept("Bool operator", {
        case LEQ() => Leq
        case LE() => Le
        case GEQ() => Geq
        case GE() => Ge
        case EQUALS() => Eq
      })
    }

  private lazy val identifier: PackratParser[IDENTIFIER] = 
    positioned {
      accept("identifier", { case id @ IDENTIFIER(_) => id })
    }

  private lazy val integer: PackratParser[INTEGER] = 
    positioned {
      accept("integer", { case lit @ INTEGER(_) => lit })
    }

  private lazy val tau: PackratParser[Tau] =
    positioned {
      accept("tau", { case TAU() => Tau() })
    }
  
  lazy val valueCCS: PackratParser[ValueCCS] =
    positioned { sum | constant | inputCh | outputCh | tauCh | ifThen | par | restrict }

  lazy val constant: PackratParser[ValueCCS] = positioned {
    identifier ~ (LBRACKET() ~> rep1sep(aexpr, COMMA()) <~ RBRACKET()).? ^^ {
      case IDENTIFIER(name) ~ None => Constant(name, None)
      case IDENTIFIER(name) ~ Some(l) => Constant(name, Some(l))
    }
  }

  lazy val inputCh: PackratParser[ValueCCS] = positioned {
    identifier ~ (LBRACKET() ~> identifier <~ RBRACKET()).? ~ (SEPARATOR() ~> valueCCS) ^^ {
      case IDENTIFIER(ch) ~ Some(IDENTIFIER(name)) ~ proc => InputCh(Channel(ch), Some(Variable(name)), proc)
      case IDENTIFIER(ch) ~ None ~ proc => InputCh(Channel(ch), None, proc)
    }
  }

  lazy val outputCh: PackratParser[ValueCCS] = positioned {
    OUT() ~> identifier ~ (LBRACKET() ~> aexpr <~ RBRACKET()).? ~ (SEPARATOR() ~> valueCCS) ^^ {
      case IDENTIFIER(name) ~ e ~ proc => OutputCh(Channel(name), e, proc)
    }
  }

  lazy val tauCh: PackratParser[ValueCCS] = positioned {
    tau ~ (SEPARATOR() ~> valueCCS) ^^ {
      case in ~ proc => InputCh(in, None, proc)
    }
  }

  lazy val ifThen: PackratParser[ValueCCS] = positioned {
    ((IF() ~ LBRACKET().?) ~> bexpr <~ (RBRACKET()).?) ~ (THEN() ~> valueCCS) ^^ {
      case b ~ proc => IfThen(b, proc)
    }
  }

  lazy val par: PackratParser[ValueCCS] = positioned {
    valueCCS ~ (PAR() ~> valueCCS) ^^ { case l ~ r => Par(l, r) }
  }

  lazy val sum: PackratParser[ValueCCS] = positioned {
    rep1sep(valueCCS, SUM()) ^^ { case l => Sum(l) } | integer ^^ { case INTEGER(0) => Sum(List.empty)}
  }

  lazy val restrict: PackratParser[ValueCCS] = positioned {
    valueCCS ~ ( (RESTR() ~ LCBRACKET()) ~> rep1sep(identifier, COMMA()) <~ RCBRACKET() ) ^^ {
      case proc ~ l => Restrict(proc, l.map{case IDENTIFIER(name) => Channel(name)})
    }
  }
