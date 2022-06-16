package main.scala.ast

import Aexpr._


// EBNF (Extended Backus–Naur form):
// boolbinop -> term [(&& | ||) term]*
// term -> '!' boolbinop | exprbinop | '(' boolbinop ')'
// exprbinop -> aexpr (< | > | ...) aexpr
object Bexpr {

  enum UnLogicOperator:
    override def toString: String =
      this match {
        case _ => "!"
      }
    case Neq


  enum LogicOperator:
    override def toString: String =
      this match {
        case Land => "&&"
        case Lor => "||"
      }

    case Land
    case Lor

  enum BoolOperator:
    override def toString: String =
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
  
  final case class Bexpr(b: BoolBinOp | BoolTerm | ExprBinOp):
    override def toString: String = this.b.toString
      
  final case class BoolBinOp(left: BoolTerm, rest: List[(LogicOperator, BoolTerm)]):
    override def toString: String =
      if this.rest.isEmpty then
        this.left.toString
      else
        this.left.toString + " " +  this.rest.map((o, t) => o.toString + " " + t.toString).mkString

  final case class ExprBinOp(left: Aexpr, op: BoolOperator, right: Aexpr):
    override def toString: String = 
      this.left.toString + " " + this.op.toString + " " + this.right.toString

  enum BoolTerm:
    case UnOp(op: UnLogicOperator, b: BoolBinOp)
    case BoolExpr(b: ExprBinOp)
    case ParBoolOp(b: BoolBinOp)

    override def toString: String =
      this match
        case UnOp(op, b) => op.toString + b.toString
        case BoolExpr(b) => b.toString
        case ParBoolOp(b) => "(" + b.toString + ")"

}
