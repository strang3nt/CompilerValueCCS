package main.scala.eval

import main.scala.ast.Aexpr._

object EvalAexpr:
  def eval(a: Aexpr): Int =
    a match
      
      case Expr(t, ts) =>
        ts.foldLeft(eval(t)){case(acc, (op, x)) => op.applyOperator(acc, eval(x))}

      case Term(f, fs) =>
        fs.foldLeft(eval(f)){case(acc, (op, x)) => op.applyOperator(acc, eval(x))}

      case Factor.Parenthesis(e) => eval(e)
      
      case Factor.NUMBER(n) => n.toString.toInt
      
      case Factor.ID(x) => 
        throw new Exception(s"Aexpr evaluation: found variable $x: during evaluation arithmetic expressions must not contain variables.")
