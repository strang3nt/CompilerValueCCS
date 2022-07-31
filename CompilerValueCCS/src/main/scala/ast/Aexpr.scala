package main.scala.ast

import main.scala.ast.CommonAst.Variable
import main.scala.eval.ApplyOperator

object Aexpr:

  sealed trait ExprOperator extends ApplyOperator[Natural, Natural], ValueCCS:
    override def applyOperator(x: Natural, y: Natural): Natural =
      this match
        case Add => x + y
        case Sub => x - y
        case Mul => x * y
        case Div => x / y

  sealed trait Summation extends ExprOperator
  case object Add extends Summation:
    override def toString: String = " + "
  case object Sub extends Summation:
    override def toString: String = " - "

  sealed trait Product extends ExprOperator
  case object Mul extends Product:
    override def toString: String = " * "
  case object Div extends Product:
    override def toString: String = " \\ "

  sealed trait Aexpr extends ValueCCS:

    def containsVariable: Boolean =
      this match
        case Expr(t, ts) =>
          if ts.isEmpty then t.containsVariable
          else t.containsVariable || ts.exists((_, t) => t.containsVariable)
        case Term(f, fs) =>
          if fs.isEmpty then f.containsVariable
          else f.containsVariable || fs.exists((_, f) => f.containsVariable)
        case Factor.ID(_) => true
        case _            => false

  final case class Expr(t: Term, ts: List[(Summation, Term)]) extends Aexpr:

    override def toString: String =
      if this.ts.isEmpty then this.t.toString
      else
        this.t.toString + this.ts
          .map((o, t) => o.toString + t.toString)
          .mkString

  final case class Term(f: Factor, fs: List[(Product, Factor)]) extends Aexpr:

    override def toString: String =
      if this.fs.isEmpty then this.f.toString
      else
        this.f.toString + this.fs
          .map((o, f) => o.toString + f.toString)
          .mkString

  enum Factor extends Aexpr:
    case Parenthesis(e: Expr)
    case NUMBER(n: Natural)
    case ID(v: Variable)

    override def toString: String =
      this match {
        case Parenthesis(e) => "(" + e.toString + ")"
        case NUMBER(n)      => n.toString
        case ID(v)          => v.toString
      }
