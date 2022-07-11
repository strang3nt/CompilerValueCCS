package main.scala.eval

import main.scala.ast.Bexpr._
import main.scala.ast.CommonAst.Variable
import main.scala.ast.Natural

import scala.collection.immutable.Map

object EvalBexpr:

  def eval(b: Bexpr, subst: Map[Variable, Natural]): Boolean =
    b match
      case BoolBinOp(left, rest) =>
        rest.foldLeft(eval(left, subst)) { case (acc, (op, x)) =>
          op.applyOperator(acc, eval(x, subst))
        }

      case BoolTerm.UnOp(op, BoolBinOp(left, rest)) =>
        rest.foldLeft(op.applyOperator(eval(left, subst))) {
          case (acc, (op, x)) => op.applyOperator(acc, eval(x, subst))
        }

      case BoolTerm.BoolExpr(b) =>
        eval(b, subst)

      case BoolTerm.ParBoolOp(b) =>
        eval(b, subst)

      case ExprBinOp(left, op, right) =>
        op.applyOperator(
          EvalAexpr.eval(left, subst),
          EvalAexpr.eval(right, subst)
        )
