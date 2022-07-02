package main.scala.process

enum CCSSystem:
  case PureCCSSystem(l: List[PureCCSProcess])
  case ValueCCSSystem(l: List[ValueCCSProcess])

  override def toString() =
    this match {
      case PureCCSSystem(s)  => s.mkString("\n")
      case ValueCCSSystem(s) => s.mkString("\n")
    }
