package main.scala.ast

import main.scala.ast.Aexpr.Aexpr
import main.scala.ast.Bexpr.Bexpr
import main.scala.ast.CommonAst._

// override def toString: String =
//   this match {
//     case Const(name, l) =>
//       name ++ l.map { "(" ++ _.mkString(", ") ++ ")" }.getOrElse("")
//     case TauCh(p) =>
//       "tau" + " . " + p.toString
//     case InputCh(c, v, p) =>
//       c.toString ++ v
//         .map { "(" ++ _.toString ++ ")" }
//         .getOrElse("") ++ " . " ++ p.toString
//     case OutputCh(c, e, p) =>
//       "'" + c.toString ++ e
//         .map { "(" ++ _.toString ++ ")" }
//         .getOrElse("") ++ " . " ++ p.toString
//     case IfThen(b, p) =>
//       "if" ++ "(" ++ b.toString ++ ")" ++ " then " ++ p.toString
//     case Par(left, right)    => left.toString ++ " | " ++ right.toString
//     case Sum(l) if l.isEmpty => "0"
//     case Sum(l)              => l.mkString(" + ")
//     case Restrict(p, l) =>
//       p.toString ++ " \\ " ++ "{" ++ l.mkString(", ") ++ "}"
//     case Redirection(p, cs) =>
//       p.toString + "{" + cs
//         .map((n, o) => n.toString + "/" + o.toString)
//         .mkString(", ") + "}"
//   }

case class Const(name: String, l: Option[List[Aexpr]]) extends ValueCCS
case class TauCh(p: ValueCCS) extends ValueCCS
case class InputCh(c: Channel, v: Option[Variable], p: ValueCCS)
    extends ValueCCS
case class OutputCh(c: Channel, e: Option[Aexpr], p: ValueCCS) extends ValueCCS
case class IfThen(b: Bexpr, p: ValueCCS) extends ValueCCS
case class Par(left: ValueCCS, right: ValueCCS) extends ValueCCS
case class Sum(l: List[ValueCCS]) extends ValueCCS
case class Restrict(p: ValueCCS, l: List[Channel]) extends ValueCCS
case class Redirection(p: ValueCCS, cs: List[(Channel, Channel)])
    extends ValueCCS
