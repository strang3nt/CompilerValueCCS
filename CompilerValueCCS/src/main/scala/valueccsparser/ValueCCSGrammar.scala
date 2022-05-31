package main.scala.valueccsparser

object grammar:

  final case class Variable(name : String)
  
  enum Natural:
    case One
    case Two
    case Three
    case Four
    case Five

  final case class Channel (name : String)

  enum BoolOperator:
    case Leq
    case Le
    case Geq
    case Ge
    case Land
    case Lor
    case Neq
    case Eq

  enum ExprOperator:
    case Add
    case Sub
    case Mul
    case Div

  enum Aexpr:
    case Var(v : Variable)
    case Num (n : Natural)
    case BinOp (left : Aexpr, op : ExprOperator, right : Aexpr)

  enum Bexpr:
    case UnOp (op : BoolOperator, right : Bexpr)
    case BoolBinOp (left : Bexpr, op : BoolOperator, right : Bexpr)
    case ExprBinOp (left : Aexpr, op : BoolOperator, right : Aexpr)

  enum ValueCCSProcess:
    case Constant (name : String, l : Option[List[Aexpr]])
    case InputCh (c : Channel, v : Option[Variable], p : Option[ValueCCSProcess])
    case OutputCh (c : Channel, e : Option[Aexpr], p : Option[ValueCCSProcess])
    case IfThen (b : Bexpr, p : ValueCCSProcess)
    case Par(left: ValueCCSProcess, right : ValueCCSProcess)
    case Sum(l : List[ValueCCSProcess])
    case Restrict(p : ValueCCSProcess, l : List[Channel])
