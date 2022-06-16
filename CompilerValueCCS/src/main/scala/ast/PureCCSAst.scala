package main.scala.ast

import CommonAst._

object PureCCSAst:
  
  enum PureCCS:

    override def toString: String =
      this match {
        case Constant (name) => name
        case InputCh (c, p) => c.toString ++ " . " ++ p.map{_.toString}.getOrElse("")
        case OutputCh (c, p) => c.toString ++ " . " ++ p.map{_.toString}.getOrElse("")
        case Par(left, right) => left.toString ++ " | " ++ right.toString
        case Sum(l) => l.mkString(", ")
        case Restrict(p, l) => p.toString ++ " \\ " ++ " {" ++ l.mkString(", ") ++ "}"
      }    

    case Constant (name : String)
    case InputCh (c : Channel, p : Option[PureCCS])
    case OutputCh (c : Channel, p : Option[PureCCS])
    case Par(left: PureCCS, right : PureCCS)
    case Sum(l : List[PureCCS])
    case Restrict(p : PureCCS, l : List[Channel])
