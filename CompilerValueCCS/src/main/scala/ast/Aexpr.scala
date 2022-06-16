package main.scala.ast

import CommonAst.Variable
import main.scala.ast.Eval
import main.scala.ast.ContainsVariable
import main.scala.ast.ApplyOperator

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

  final case class Aexpr(e: Expr | Term | Factor) extends Eval[Int], ContainsVariable:
    override def toString: String = this.e.toString
    override def eval: Option[Int] = this.e.eval
    override def containsVariable: Boolean = this.e.containsVariable

  enum Expr extends Eval[Int], ContainsVariable:
    case Summation(t: Term, ts: List[(Add.type | Sub.type, Term)])

    override def toString: String =
      this match
        case Summation(t, ts) if ts.isEmpty =>
          t.toString
        case Summation(t, ts) =>
          t.toString + ts.map((o, t) => o.toString + t.toString).mkString
    override def eval: Option[Int] =
      this match
        case _ if this.containsVariable =>
          None
        case Summation(t, ts) if ts.isEmpty =>
          t.eval
        case Summation(t, ts) =>
          ts.foldLeft(t.eval){case(acc, (op, x)) => Some (op.applyOperator(acc.get, x.eval.get))}

    override def containsVariable: Boolean =
      this match
        case Summation(t, ts) if ts.isEmpty =>
          t.containsVariable
        case Summation(t, ts) =>
          t.containsVariable || ts.exists((_, t) => t.containsVariable)

  enum Term extends Eval[Int], ContainsVariable:
    case Multiplication(f: Factor, fs: List[(Mul.type | Div.type, Factor)])
    
    override def containsVariable: Boolean =
      this match
        case Multiplication(f, fs) if fs.isEmpty =>
          f.containsVariable
        case Multiplication(f, fs) =>
          f.containsVariable || fs.exists((_, f) => f.containsVariable)

    override def toString: String =
      this match
        case Multiplication(f, fs) if fs.isEmpty =>
          f.toString
        case Multiplication(f, fs) =>
          f.toString + fs.map((o, f) => o.toString + f.toString).mkString
    override def eval: Option[Int] =
      this match
        case _ if this.containsVariable =>
          None
        case Multiplication(f, fs) if fs.isEmpty =>
          f.eval
        case Multiplication(f, fs) =>
          fs.foldLeft(f.eval){case(acc, (op, x)) => Some(op.applyOperator(acc.get, x.eval.get))}


  enum Factor extends Eval[Int], ContainsVariable:
    case Parenthesis(e: Expr)
    case NUMBER(n: Natural)
    case ID(v: Variable)

    override def toString: String =
      this match {
        case Parenthesis(e) => "(" + e.toString + ")"
        case NUMBER(n) => n.toString
        case ID(v) => v.toString
      }
    
    override def containsVariable: Boolean =
      this match
        case ID(_) => true
        case _ => false
    override def eval: Option[Int] =
      this match
        case ID(_) => None
        case NUMBER(n) => Some(n.toString.toInt)
        case Parenthesis(e) => e.eval

}
