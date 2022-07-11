package main.scala.ast

import main.scala.ast.Aexpr._
import main.scala.ast.CommonAst._
import main.scala.eval.{ApplyOperator, ApplyUnaryOperator}

import scala.util.parsing.input.Positional

object Bexpr:

  enum UnLogicOperator extends ApplyUnaryOperator[Boolean, Boolean], Positional:
    override def toString: String =
      this match {
        case _ => "!"
      }
    override def applyOperator(x: Boolean): Boolean =
      this match {
        case _ => !x
      }
    case Neq

  enum LogicOperator extends ApplyOperator[Boolean, Boolean], Positional:
    override def toString: String =
      this match {
        case Land => "&&"
        case Lor  => "||"
      }
    override def applyOperator(x: Boolean, y: Boolean): Boolean =
      this match
        case Land => x && y
        case Lor  => x || y

    case Land
    case Lor

  enum BoolOperator extends ApplyOperator[Natural, Boolean], Positional:
    override def toString: String =
      this match
        case Leq => "<="
        case Le  => "<"
        case Geq => ">="
        case Ge  => ">"
        case Eq  => "=="

    override def applyOperator(x: Natural, y: Natural): Boolean =
      this match
        case Leq => x <= y
        case Le  => x < y
        case Geq => x >= y
        case Ge  => x > y
        case Eq  => x == y

    case Leq
    case Le
    case Geq
    case Ge
    case Eq

  sealed trait Bexpr extends Positional:

    def containsVariable: Boolean =
      this match
        case BoolBinOp(left, rest) =>
          if rest.isEmpty then left.containsVariable
          else
            left.containsVariable || rest.exists((_, b) => b.containsVariable)
        case ExprBinOp(left, op, right) =>
          left.containsVariable || right.containsVariable
        case BoolTerm.UnOp(op, b)  => b.containsVariable
        case BoolTerm.BoolExpr(b)  => b.containsVariable
        case BoolTerm.ParBoolOp(b) => b.containsVariable

  final case class BoolBinOp(
      left: BoolTerm,
      rest: List[(LogicOperator, BoolTerm)]
  ) extends Bexpr:

    override def toString: String =
      if this.rest.isEmpty then this.left.toString
      else
        this.left.toString + " " + this.rest
          .map((o, t) => o.toString + " " + t.toString)
          .mkString

  final case class ExprBinOp(left: Aexpr, op: BoolOperator, right: Aexpr)
      extends Bexpr:

    override def toString: String =
      this.left.toString + " " + this.op.toString + " " + this.right.toString

  enum BoolTerm extends Bexpr:
    case UnOp(op: UnLogicOperator, b: BoolBinOp)
    case BoolExpr(b: ExprBinOp)
    case ParBoolOp(b: BoolBinOp)

    override def toString: String =
      this match
        case UnOp(op, b)  => op.toString + b.toString
        case BoolExpr(b)  => b.toString
        case ParBoolOp(b) => "(" + b.toString + ")"
