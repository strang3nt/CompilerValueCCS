package main.scala.ast

import CommonAst._

object ValueCCSAst:

  final case class Variable(name : String):
    override def toString() = name
  
  enum Natural:

    override def toString() =
      this match {
        case One => "1"
        case Two => "2"
        case Three => "3"
        case Four => "4"
        case Five => "5"
      }

    case One
    case Two
    case Three
    case Four
    case Five

  enum BoolOperator:

    override def toString() =
      this match {
        case Leq => "<="
        case Le => "<"
        case Geq => ">="
        case Ge => ">"
        case Land => "&&"
        case Lor => "||"
        case Neq => "!"
        case Eq => "=="
      }

    case Leq
    case Le
    case Geq
    case Ge
    case Land
    case Lor
    case Neq
    case Eq

  enum ExprOperator:
    
    override def toString() =
      this match {
        case Add => "+"
        case Sub => "-"
        case Mul => "*"
        case Div => "\\"
      }

    case Add
    case Sub
    case Mul
    case Div

  enum Aexpr:

    override def toString() =
      this match {
        case Var (v) => v.toString
        case Num (n) => n.toString
        case BinOp (left: (Num | Var), op, right: (Num | Var)) => left.toString ++ " " ++ op.toString ++ " " ++ right.toString
        case BinOp (left, op, right) => 
          "(" ++ left.toString ++ ")" ++ " " ++ op.toString ++ " " ++ "(" ++ right.toString ++ ")"
      }
      
    case Var(v : Variable)
    case Num (n : Natural)
    case BinOp (left : Aexpr, op : ExprOperator, right : Aexpr)

  enum Bexpr:

    override def toString() =
      this match {
        case UnOp (op, right) => op.toString ++ right.toString
        case BoolBinOp (left, op, right) => "(" ++ left.toString ++ ")" ++ " " ++ op.toString ++ " " ++ "(" ++ right.toString ++ ")"
        case ExprBinOp (left, op, right) => left.toString ++ " " ++ op.toString ++ " " ++ right.toString
      }
    
    case UnOp (op : BoolOperator, right : Bexpr)
    case BoolBinOp (left : Bexpr, op : BoolOperator, right : Bexpr)
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
