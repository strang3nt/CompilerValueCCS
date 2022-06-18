package main.scala.ast

import CommonAst.Variable
import main.scala.eval.ApplyOperator

// EBNF (Extended Backus–Naur form):
// expr -> term [ (‘+’ | ‘-’) term ]*
// term -> factor [ (‘*’ | ‘/’) factor ]*
// factor -> ‘(‘ expr ‘)’ | ID | NUMBER
object Aexpr {

  enum Natural:

    override def toString: String =
      this match {
        case One => "1"
        case Two => "2"
        case Three => "3"
        case Four => "4"
        case Five => "5"
      }
    
    case One
    case Two
    case Three
    case Four
    case Five

  sealed trait ExprOperator extends ApplyOperator[Int, Int]:
    override def applyOperator(x: Int, y: Int): Int =
      this match
        case Add => x + y
        case Sub => x - y
        case Mul => x * y
        case Div => x / y
  case object Add extends ExprOperator:
    override def toString: String = " + "
  case object Sub extends ExprOperator:
    override def toString: String = " - "
  case object Mul extends ExprOperator:
    override def toString: String = " * "
  case object Div extends ExprOperator:
    override def toString: String = " \\ "

  sealed trait Aexpr:
    def containsVariable: Boolean =
      this match
        case Expr(t, ts) =>
          if ts.isEmpty then
            t.containsVariable
          else
            t.containsVariable || ts.exists((_, t) => t.containsVariable)
        case Term(f, fs) =>
          if fs.isEmpty then
            f.containsVariable
          else
            f.containsVariable || fs.exists((_, f) => f.containsVariable)
        case Factor.ID(_) => true
        case _ => false
  
  final case class Expr(t: Term, ts: List[(Add.type | Sub.type, Term)]) extends Aexpr:

    override def toString: String =
      if this.ts.isEmpty then
        this.t.toString
      else
        this.t.toString + this.ts.map((o, t) => o.toString + t.toString).mkString

  final case class Term(f: Factor, fs: List[(Mul.type | Div.type, Factor)]) extends Aexpr:
    
    override def toString: String =
      if this.fs.isEmpty then
        this.f.toString
      else
        this.f.toString + this.fs.map((o, f) => o.toString + f.toString).mkString

  enum Factor extends Aexpr:
    case Parenthesis(e: Expr)
    case NUMBER(n: Natural)
    case ID(v: Variable)

    override def toString: String =
      this match {
        case Parenthesis(e) => "(" + e.toString + ")"
        case NUMBER(n) => n.toString
        case ID(v) => v.toString
      }

}
