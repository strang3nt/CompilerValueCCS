package main.scala.eval

import scala.collection.immutable.Map

import main.scala.ast.Aexpr._
import main.scala.ast.Naturals._
import main.scala.ast.CommonAst.Variable

object EvalAexpr:
  def eval(a: Aexpr, subst: Map[Variable, Natural]): Natural =
    a match
      
      case Expr(t, ts) =>
        ts.foldLeft(eval(t, subst)){case(acc, (op, x)) => op.applyOperator(acc, eval(x, subst))}

      case Term(f, fs) =>
        fs.foldLeft(eval(f, subst)){case(acc, (op, x)) => op.applyOperator(acc, eval(x, subst))}

      case Factor.Parenthesis(e) => eval(e, subst)
      
      case Factor.NUMBER(n) => n
      
      case Factor.ID(x) if subst.contains(x) =>
        // subst guaranteed to contain Variable x
        subst.get(x).get

      case Factor.ID(x) =>
        throw new Exception(s"EvalAexpr: found variable $x: no suitable substitution found, there must not be free variables during eval.")
