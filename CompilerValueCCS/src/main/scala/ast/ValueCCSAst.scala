package main.scala.ast

import main.scala.ast.Aexpr.Aexpr
import main.scala.ast.Bexpr.Bexpr
import main.scala.ast.CommonAst._

import scala.util.parsing.input.Positional

enum ValueCCS extends Positional:

  override def toString: String =
    this match {
      case Constant(name, l) =>
        name ++ l.map { "(" ++ _.mkString(", ") ++ ")" }.getOrElse("")
      case TauCh(p) =>
        "tau" + " . " + p.toString
      case InputCh(c, v, p) =>
        c.toString ++ v
          .map { "(" ++ _.toString ++ ")" }
          .getOrElse("") ++ " . " ++ p.toString
      case OutputCh(c, e, p) =>
        "'" + c.toString ++ e
          .map { "(" ++ _.toString ++ ")" }
          .getOrElse("") ++ " . " ++ p.toString
      case IfThen(b, p) =>
        "if" ++ "(" ++ b.toString ++ ")" ++ " then " ++ p.toString
      case Par(left, right)    => left.toString ++ " | " ++ right.toString
      case Sum(l) if l.isEmpty => "0"
      case Sum(l)              => l.mkString(" + ")
      case Restrict(p, l) =>
        p.toString ++ " \\ " ++ "{" ++ l.mkString(", ") ++ "}"
      case Redirection(p, cs) =>
        p.toString + "{" + cs
          .map((n, o) => n.toString + "/" + o.toString)
          .mkString(", ") + "}"
    }

  case Constant(name: String, l: Option[List[Aexpr]])
  case TauCh(p: ValueCCS)
  case InputCh(c: Channel, v: Option[Variable], p: ValueCCS)
  case OutputCh(c: Channel, e: Option[Aexpr], p: ValueCCS)
  case IfThen(b: Bexpr, p: ValueCCS)
  case Par(left: ValueCCS, right: ValueCCS)
  case Sum(l: List[ValueCCS])
  case Restrict(p: ValueCCS, l: List[Channel])
  case Redirection(p: ValueCCS, cs: List[(Channel, Channel)])
