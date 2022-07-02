package main.scala.parser

// import main.scala.parser.ValueCCSParser.{ valueCCS, variable, op, cp }
import main.scala.ast.ValueCCSAst.ValueCCS
import main.scala.process.{Constant, ValueCCSProcess}
import main.scala.process.CCSSystem.ValueCCSSystem

object ValueCCSProcParser
// def parse(input : String): ValueCCSSystem =
//   ValueCCSSystem(
//     input
//       .split("\n")
//       .map((i) =>
//         valueCCSProc
//           .parse(i.filterNot(_.isWhitespace))
//           .map(t => if t._1.length > 0 then throw new Exception("Input was not parsed entirely") else t._2))
//           .map(_.getOrElse(throw new Exception("Could not parse the input")))
//       .toList)

// private[parser] val constant: Parser[Constant] =
//   val constantName = (Parser.charWhere(_.isUpper) ~ alpha.rep0).string
//   (constantName ~ (op *> variable.repSep(Parser.char(',')) <* cp).?)
//     .map{case (name, list) => Constant (name, list.map { _.toList })}

// private[parser] val valueCCSProc: Parser[ValueCCSProcess] =
//   (constant ~ valueCCS).map((c, v) => ValueCCSProcess(c, v))
