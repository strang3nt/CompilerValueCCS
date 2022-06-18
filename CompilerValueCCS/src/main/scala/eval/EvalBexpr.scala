package main.scala.eval

import main.scala.ast.Bexpr._
import main.scala.eval.EvalAexpr._

object EvalBexpr {
  
  def eval(b: Bexpr): Boolean =
    b match
      case BoolBinOp(left, rest) =>
        if rest.isEmpty then
          eval(left)
        else rest.foldLeft(eval(left)){case(acc, (op, x)) => op.applyOperator(acc, eval(x))}

      case BoolTerm.UnOp(op, b) =>
        op.applyOperator(eval(b))

      case BoolTerm.BoolExpr(b) =>
        eval(b)
      
      case BoolTerm.ParBoolOp(b) =>
        eval(b)

      case ExprBinOp(left, op, right) =>
        op.applyOperator(EvalAexpr.eval(left), EvalAexpr.eval(right))

}
