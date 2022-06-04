package main.scala.ast

import CommonAst._
import Aexpr._

object ValueCCSAst:

  enum UnLogicOperator:
    override def toString() =
      this match {
        case _ => "!"
      }
    case Neq


  enum LogicOperator:
    override def toString() =
      this match {
        case Land => "&&"
        case Lor => "||"
      }

    case Land
    case Lor

  enum BoolOperator:
    override def toString() =
      this match {
        case Leq => "<="
        case Le => "<"
        case Geq => ">="
        case Ge => ">"
        case Eq => "=="
      }

    case Leq
    case Le
    case Geq
    case Ge
    case Eq

  enum Bexpr:
    override def toString() =
      this match {
        case UnOp (op, right) => op.toString ++ right.toString
        case BoolBinOp (left, op, right) => "(" ++ left.toString ++ ")" ++ " " ++ op.toString ++ " " ++ "(" ++ right.toString ++ ")"
        case ExprBinOp (left, op, right) => left.toString ++ " " ++ op.toString ++ " " ++ right.toString
      }
    
    case UnOp (op : UnLogicOperator, right : Bexpr)
    case BoolBinOp (left : Bexpr, op : LogicOperator, right : Bexpr)
    case ExprBinOp (left : Aexpr, op : BoolOperator, right : Aexpr)

  enum ValueCCS:

    override def toString() =
      this match {
        case Constant (name, l) => name ++ l.map {"(" ++ _.mkString(", ") ++ ")"}.getOrElse("")
        case InputCh (c, v, p) => c.toString ++ v.map{"(" ++ _.toString ++ ")"}.getOrElse("") ++ " . " ++ p.map{_.toString}.getOrElse("")
        case OutputCh (c, e, p) => c.toString ++ e.map{"(" ++ _.toString ++ ")"}.getOrElse("") ++ " . " ++ p.map{_.toString}.getOrElse("")
        case IfThen (b, p) => "if" ++ "(" ++ b.toString ++ ")" ++ " then " ++ p.toString
        case Par(left, right) => left.toString ++ " | " ++ right.toString
        case Sum(l) => l.mkString(", ")
        case Restrict(p, l) => p.toString ++ " \\ " ++ " {" ++ l.mkString(", ") ++ "}"
      }    

    case Constant (name : String, l : Option[List[Aexpr]])
    case InputCh (c : Channel, v : Option[Variable], p : Option[ValueCCS])
    case OutputCh (c : Channel, e : Option[Aexpr], p : Option[ValueCCS])
    case IfThen (b : Bexpr, p : ValueCCS)
    case Par(left: ValueCCS, right : ValueCCS)
    case Sum(l : List[ValueCCS])
    case Restrict(p : ValueCCS, l : List[Channel])
