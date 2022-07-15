package main.scala.ast

import main.scala.ast.CommonAst._

enum PureCCS:

  override def toString: String =
    this match {
      case Constant(name)      => name
      case InputCh(c, p)       => c.toString ++ " . " ++ p.toString
      case OutputCh(c, p)      => c.toString ++ " . " ++ p.toString
      case TauCh(p)            => "tau" ++ " . " ++ p.toString
      case Par(left, right)    => left.toString ++ " | " ++ right.toString
      case Sum(l) if l.isEmpty => "0"
      case Sum(l)              => l.mkString(" + ")
      case Restrict(p, l) =>
        p.toString ++ " \\ " ++ " {" ++ l.mkString(", ") ++ "}"
      case Redirection(p, cs) =>
        p.toString + "[" + cs
          .map((n, o) => n.toString + "/" + o.toString)
          .mkString(", ") + "]"

    }

  case Constant(name: String)
  case TauCh(p: PureCCS)
  case InputCh(c: Channel, p: PureCCS)
  case OutputCh(c: Channel, p: PureCCS)
  case Par(left: PureCCS, right: PureCCS)
  case Sum(l: List[PureCCS])
  case Restrict(p: PureCCS, l: List[Channel])
  case Redirection(p: PureCCS, cs: List[(Channel, Channel)])
