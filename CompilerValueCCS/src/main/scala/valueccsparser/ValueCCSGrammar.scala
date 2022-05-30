package main.scala.valueccsparser

object grammar:

  final case class Variable(name : String)

  final case class Natural (n : Int)

  enum Aexpr:
    case Var(v : Variable)
    case Num (n : Natural)
    case BinOp (left : Aexpr, op : String, right : Aexpr)

  enum Bexpr:
    case UnOp (op : String, right : Bexpr)
    case BoolBinOp (left : Bexpr, op : String, right : Bexpr)
    case ExprBinOp (left : Aexpr, op : String, right : Aexpr)

  final case class Channel (name : String)

  enum ValueCCSProcess:
    case Constant (name : String, l : Option[List[Aexpr]])
    case InputCh (c : Channel, v : Option[Variable], p : Option[ValueCCSProcess])
    case OutputCh (c : Channel, e : Option[Aexpr], p : Option[ValueCCSProcess])
    case IfThen (b : Bexpr, p : ValueCCSProcess)
    case Par(left: ValueCCSProcess, right : ValueCCSProcess)
    case Sum(l : List[ValueCCSProcess])
    case Restrict(p : ValueCCSProcess, l : List[Channel])
