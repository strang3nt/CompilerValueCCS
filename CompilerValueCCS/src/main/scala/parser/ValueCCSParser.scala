package main.scala.parser

import main.scala.ast._
import main.scala.ast.Aexpr._
import main.scala.ast.Aexpr.Factor._
import main.scala.ast.Bexpr._
import main.scala.ast.Bexpr.BoolOperator._
import main.scala.ast.Bexpr.BoolTerm._
import main.scala.ast.Bexpr.LogicOperator._
import main.scala.ast.Bexpr.UnLogicOperator._
import main.scala.ast.CommonAst._
import main.scala.ast.ValueCCS._
import main.scala.process.{ValueCCSProcess, Constant as ProcessConstant}

import scala.util.parsing.combinator.{PackratParsers, Parsers}
import scala.util.parsing.input.{NoPosition, Position, Reader}

object ValueCCSParser extends Parsers with PackratParsers:
  override type Elem = CCSToken

  class CCSTokenReader(tokens: Seq[CCSToken]) extends Reader[CCSToken] {
    override def first: CCSToken = tokens.head
    override def atEnd: Boolean = tokens.isEmpty
    override def pos: Position =
      tokens.headOption.map(_.pos).getOrElse(NoPosition)
    override def rest: Reader[CCSToken] = new CCSTokenReader(tokens.tail)
  }

  def apply[A](
      tokens: Seq[CCSToken],
      program: PackratParser[A]
  ): Either[((Int, Int), String), A] = {
    val reader = new PackratReader(new CCSTokenReader(tokens))
    program(reader) match {
      case NoSuccess(msg, next) => Left(((next.pos.line, next.pos.column), msg))
      case Success(result, next) => Right(result)
      case err @ _ => throw new Exception(s"ValueCCSParser: Fatal: $err")
    }
  }

  lazy val program: PackratParser[ValueCCSProcess] = phrase(process_constant ~ DEF ~ valueCCS ^^ {
    case c ~ _ ~ v => ValueCCSProcess(c, v)})

  lazy val valueCCS: PackratParser[ValueCCS] =
    positioned {
      par | sum | restrict | inputCh | outputCh | tauCh | ifThen | constant
    }

  def aexpr = positioned {
    lazy val parenthesis =
      LBRACKET ~> expr <~ RBRACKET ^^ (Parenthesis(_))
    lazy val number = integer ^^ { case INTEGER(nat) => NUMBER(Natural(nat)) }
    lazy val id = small_case_identifier ^^ { case IDENTIFIER(name) =>
      ID(Variable(name))
    }
    lazy val factor = number | parenthesis | id
    lazy val term = factor ~ ((DIV | MUL) ~ factor).* ^^ {
      case f ~ l if l.isEmpty => Term(f, List.empty)
      case f ~ l =>
        Term(
          f,
          l.map {
            case DIV ~ x => (Div, x)
            case MUL ~ x => (Mul, x)
            case op ~ _ =>
              throw new Exception("Could not parse operator " + op.toString)
          }
        )
    }

    lazy val expr: Parser[Expr] = term ~ ((SUM | SUB) ~ term).* ^^ {
      case t ~ l if l.isEmpty => Expr(t, List.empty)
      case t ~ l =>
        Expr(
          t,
          l.map {
            case SUM ~ x => (Add, x)
            case SUB ~ x => (Sub, x)
            case op ~ _ =>
              throw new Exception("Could not parse operator " + op.toString)
          }
        )
    }
    expr | factor | term
  }

  def bexpr = positioned {
    lazy val parBoolOp =
      LBRACKET ~> boolBinOp <~ RBRACKET ^^ (ParBoolOp(_))
    lazy val boolExpr = exprBinOp ^^ (BoolExpr(_))
    lazy val unOp = unLogicOperator ~ boolBinOp ^^ { case op ~ b =>
      UnOp(op, b)
    }
    lazy val boolTerm: Parser[BoolTerm] = parBoolOp | boolExpr | unOp
    lazy val boolBinOp = boolTerm ~ (logicOperator ~ boolTerm).* ^^ {
      case b ~ l if l.isEmpty => BoolBinOp(b, List.empty)
      case b ~ l              => BoolBinOp(b, l.map { case op ~ b => (op, b) })
    }
    lazy val exprBinOp = aexpr ~ boolOperator ~ aexpr ^^ { case l ~ o ~ r =>
      ExprBinOp(l, o, r)
    }
    boolBinOp | boolTerm | exprBinOp
  }

  private def unLogicOperator =
    positioned {
      accept("Unary logic operator", { case NOT => Neq })
    }

  private def logicOperator =
    positioned {
      accept(
        "Logic operator",
        {
          case OR  => Lor
          case AND => Land
        }
      )
    }

  private def boolOperator =
    positioned {
      accept(
        "Bool operator",
        {
          case LEQ    => Leq
          case LE     => Le
          case GEQ    => Geq
          case GE     => Ge
          case EQUALS => Eq
        }
      )
    }

  private def small_case_identifier =
    positioned {
      accept(
        "identifier starting with small character",
        { case id @ IDENTIFIER(x) if x.charAt(0).toUpper != x.charAt(0) => id }
      )
    }

  private def capital_case_identifier =
    positioned {
      accept(
        "Identifier starting with capital character",
        { case id @ IDENTIFIER(x) if x.charAt(0).toUpper == x.charAt(0) => id }
      )
    }

  private def integer =
    positioned {
      accept("integer", { case lit @ INTEGER(_) => lit })
    }

  private def tau =
    positioned {
      accept("tau", { case TAU => Tau() })
    }

  def constant = positioned {
    capital_case_identifier ~ (LBRACKET ~> rep1sep(
      aexpr,
      COMMA
    ) <~ RBRACKET).? ^^ {
      case IDENTIFIER(name) ~ None    => Constant(name, None)
      case IDENTIFIER(name) ~ Some(l) => Constant(name, Some(l))
    }
  }

  def process_constant = positioned {
    capital_case_identifier ~ (LBRACKET ~> rep1sep(
      small_case_identifier,
      COMMA
    ) <~ RBRACKET).? ^^ {
      case IDENTIFIER(name) ~ None    => ProcessConstant(name, None)
      case IDENTIFIER(name) ~ Some(l) => ProcessConstant(name, Some(l.map{ case IDENTIFIER(name) => Variable(name)}))
    }
  }

  lazy val inputCh: PackratParser[ValueCCS] = positioned {
    small_case_identifier ~
      (LBRACKET ~> small_case_identifier <~ RBRACKET).? ~
      (SEPARATOR ~> (outputCh | constant | tauCh | inputCh | (LBRACKET ~> (sum | par | restrict) <~ RBRACKET))) ^^ {
        case IDENTIFIER(ch) ~ Some(IDENTIFIER(name)) ~ proc =>
          InputCh(Channel(ch), Some(Variable(name)), proc)
        case IDENTIFIER(ch) ~ None ~ proc => InputCh(Channel(ch), None, proc)
      }
  }

  lazy val outputCh: PackratParser[ValueCCS] = positioned {
    OUT ~> small_case_identifier ~
      (LBRACKET ~> aexpr <~ RBRACKET).? ~
      (SEPARATOR ~> (outputCh | constant | tauCh | inputCh | (LBRACKET ~> (sum | par | restrict) <~ RBRACKET))) ^^ {
        case IDENTIFIER(name) ~ e ~ proc => OutputCh(Channel(name), e, proc)
      }
  }

  lazy val tauCh: PackratParser[ValueCCS] = positioned {
    tau ~
      (SEPARATOR ~> (outputCh | constant | tauCh | inputCh | (LBRACKET ~> (sum | par | restrict) <~ RBRACKET))) ^^ {
        case in ~ proc =>
          InputCh(in, None, proc)
      }
  }

  def ifThen = positioned {
    ((IF ~ LBRACKET) ~> bexpr <~ RBRACKET) ~ (THEN ~> valueCCS) ^^ {
      case b ~ proc => IfThen(b, proc)
    }
  }

  lazy val par: PackratParser[ValueCCS] = positioned {
    (sum | restrict | outputCh | inputCh | tauCh | constant | ifThen) ~ (PAR ~> valueCCS) ^^ {
      case l ~ r => Par(l, r)
    }
  }

  lazy val sum: PackratParser[ValueCCS] = positioned {
    (par | restrict | outputCh | inputCh | tauCh | constant | ifThen) ~ (SUM ~> (par | restrict | outputCh | inputCh | tauCh | constant | ifThen)).+ ^^ {
      case p ~ q => Sum(p :: q)
    } |
      integer ^^ { case INTEGER(0) => Sum(List.empty) }
  }

  lazy val restrict: PackratParser[ValueCCS] = positioned {
    valueCCS ~ ((RESTR ~ CURLY_LBRACKET) ~> rep1sep(
      small_case_identifier,
      COMMA
    ) <~ CURLY_RBRACKET) ^^ { case proc ~ l =>
      Restrict(proc, l.map { case IDENTIFIER(name) => Channel(name) })
    }
  }
