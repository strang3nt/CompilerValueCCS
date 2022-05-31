package main.scala.valueccsparser

import cats.parse.{Parser0, Parser, Numbers}
import cats.parse.Rfc5234._
import cats.parse.Numbers._
import cats.parse.Parser.string

object ValueCCSParser {

    import grammar._
    import Aexpr._
    import Bexpr._
    import ValueCCSProcess._
    import ExprOperator._
    import BoolOperator._
    import Natural._

    def parse(input : String): Either[Parser.Error, ValueCCSProcess] =
      input.filterNot(_.isWhitespace)
      parser.parse(input).map(_._2)

    private[this] val parser : Parser[ValueCCSProcess] =
      
      val separator: Parser[Unit] = Parser.char('.').void
      val lowerCaseString = Parser.charWhere(c => (c.isLower && c.isLetter)).rep.string
      
      val variable = lowerCaseString map (Variable(_))
      val channel = lowerCaseString map (Channel(_))
      val number = Parser
        .stringIn("1" :: "2" ::"3" :: "4" :: "5" :: Nil)
        .map{ 
          case "1" => One
          case "2" => Two
          case "3" => Three
          case "4" => Four
          case "5" => Five
        }

      val exprOperator = Parser
        .stringIn("\\" :: "*" :: "+" :: "-" :: Nil)
        .map {
          case "\\" => Div
          case "*" => Mul
          case "+" => Add
          case "-" => Sub
        }
      val boolOperator = Parser
        .stringIn(("&&" :: "||" :: "!" :: "==" :: "<" :: ">" :: "<=" :: ">=" :: Nil))
        .map {
          case "&&" => Land
          case "||" => Lor
          case "!" => Neq
          case "<=" => Leq
          case "==" => Eq
          case "<" => Le
          case ">" => Ge
          case ">=" => Geq
        }

      // AEXPR
      val num = number map (Num (_))
      val _var = variable map (Var(_))
      lazy val binop = Parser defer ((num | _var) ~ exprOperator ~ aexpr) map { case((l, o), r) => BinOp(l, o, r) }
      lazy val aexpr : Parser[Aexpr] = Parser.defer (binop.backtrack | num | _var )

      // BEXPR
      lazy val unOp = Parser defer (boolOperator ~ bexpr) map { case (s, b) => UnOp(s, b) }
      lazy val boolBinOp = Parser defer (bexpr ~ boolOperator ~ bexpr) map { case((l, o), r) => BoolBinOp(l, o, r) }
      lazy val exprBinOp = Parser.defer (aexpr ~ boolOperator ~ aexpr) map {case((l, o), r) => ExprBinOp(l, o, r) }
      lazy val bexpr: Parser[Bexpr] = Parser defer ( exprBinOp.backtrack | boolBinOp.backtrack | unOp )

      // VALUE CCS PROCESS
      val constantName = (Parser.charWhere(_.isUpper) ~ alpha.rep0).string
      val constant = (constantName ~ (Parser.char('(') *> aexpr.repSep(Parser.char(',')) <* Parser.char(')')).?)
        .map{case (name, list) => Constant (name, list.map { _.toList })}
      
      lazy val inputCh = Parser
        .defer((channel ~ ( Parser.char('(') *> variable <* Parser.char(')')).?) ~ (separator *> valueCCSProcess).?)
        .map{case ((ch, v), proc) => InputCh (ch, v, proc)}
      
      lazy val outputCh = Parser
        .defer((Parser.char('\'') *> channel) ~ (Parser.char('(') *> aexpr <* Parser.char(')')).? ~ (separator *> valueCCSProcess).?)
        .map { case ((ch, v), proc) => OutputCh (ch, v, proc) }
      
      lazy val ifThen = Parser
        .defer(((Parser.string("if") ~ Parser.char('(')) *> bexpr <* Parser.char(')')) ~ (Parser.string("then") *> valueCCSProcess))
        .map(IfThen (_, _))
      
      lazy val par = Parser
        .defer(valueCCSProcess ~ (Parser.char('|').void *> valueCCSProcess))
        .map(Par(_, _))
      
      lazy val sum = Parser
        .defer((valueCCSProcess.repSep(Parser.char('+'))))
        .map{ x => Sum (x.toList) }
      
      lazy val restrict = Parser
        .defer(valueCCSProcess ~ ((Parser.char('\\') ~ Parser.char('{')) *> channel.repSep(Parser.char(',')) <* Parser.char('}')))
        .map{case (proc, chs) => Restrict (proc, chs.toList)}

      lazy val valueCCSProcess: Parser[ValueCCSProcess] = Parser.oneOf(
        constant :: 
        inputCh :: 
        outputCh :: 
        ifThen :: 
        par :: 
        sum :: 
        restrict :: 
        Nil
      )

      valueCCSProcess

}
