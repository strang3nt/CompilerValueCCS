package main.scala.ast

import Aexpr._
import main.scala.ast.ContainsVariable
import main.scala.ast.Eval
import main.scala.ast.ApplyOperator
import cats.Apply


// EBNF (Extended Backus–Naur form):
// boolbinop -> term [(&& | ||) term]*
// term -> '!' boolbinop | exprbinop | '(' boolbinop ')'
// exprbinop -> aexpr (< | > | ...) aexpr
object Bexpr {

  enum UnLogicOperator extends ApplyOperator[Boolean, Boolean]:
    override def toString: String =
      this match {
        case _ => "!"
      }
    override def applyOperator(x: Boolean): Boolean = 
      this match {
        case _ => !x
      }
    case Neq


  enum LogicOperator extends ApplyOperator[Boolean, Boolean]:
    override def toString: String =
      this match {
        case Land => "&&"
        case Lor => "||"
      }
    override def applyOperator(x: Boolean, y: Boolean): Boolean =
      this match
        case Land => x && y
        case Lor => x || y

    case Land
    case Lor

  enum BoolOperator extends ApplyOperator[Int, Boolean]:
    override def toString: String =
      this match
        case Leq => "<="
        case Le => "<"
        case Geq => ">="
        case Ge => ">"
        case Eq => "=="

    override def applyOperator(x: Int, y: Int): Boolean =
      this match
        case Leq => x <= y
        case Le => x < y
        case Geq => x >= y
        case Ge => x > y
        case Eq => x == y

    case Leq
    case Le
    case Geq
    case Ge
    case Eq
  
  final case class Bexpr(b: BoolBinOp | BoolTerm | ExprBinOp) extends Eval[Boolean]:
    override def toString: String = this.b.toString
    override def eval: Option[Boolean] = this.b.eval
      
  final case class BoolBinOp(left: BoolTerm, rest: List[(LogicOperator, BoolTerm)]) extends 
    Eval[Boolean],
    ContainsVariable:
    override def toString: String =
      if this.rest.isEmpty then
        this.left.toString
      else
        this.left.toString + " " +  this.rest.map((o, t) => o.toString + " " + t.toString).mkString

    override def eval: Option[Boolean] =
      if this.containsVariable then
        None
      else if this.rest.isEmpty then
        this.left.eval
      else this.rest.foldLeft(this.left.eval){case(acc, (op, x)) => Some(op.applyOperator(acc.get, x.eval.get))}
      
    override def containsVariable: Boolean =
      if this.rest.isEmpty then
        this.left.containsVariable
      else
        this.left.containsVariable ||  this.rest.exists((_, b) => b.containsVariable)

  final case class ExprBinOp(left: Aexpr, op: BoolOperator, right: Aexpr) extends
    Eval[Boolean],
    ContainsVariable:
    override def toString: String = 
      this.left.toString + " " + this.op.toString + " " + this.right.toString
    override def eval: Option[Boolean] =
      if this.containsVariable then
        None
      else Some(op.applyOperator(left.eval.get, right.eval.get))

    override def containsVariable: Boolean = left.containsVariable || right.containsVariable

  enum BoolTerm extends
    Eval[Boolean],
    ContainsVariable:
    case UnOp(op: UnLogicOperator, b: BoolBinOp)
    case BoolExpr(b: ExprBinOp)
    case ParBoolOp(b: BoolBinOp)

    override def toString: String =
      this match
        case UnOp(op, b) => op.toString + b.toString
        case BoolExpr(b) => b.toString
        case ParBoolOp(b) => "(" + b.toString + ")"

    override def eval: Option[Boolean] =
      this match
        case _ if this.containsVariable => None
        case UnOp(op, b) => Some(op.applyOperator(b.eval.get))
        case BoolExpr(b) => Some(b.eval.get)
        case ParBoolOp(b) => Some(b.eval.get)

    override def containsVariable: Boolean =
      this match
        case UnOp(op, b) => b.containsVariable
        case BoolExpr(b) => b.containsVariable
        case ParBoolOp(b) => b.containsVariable

}
