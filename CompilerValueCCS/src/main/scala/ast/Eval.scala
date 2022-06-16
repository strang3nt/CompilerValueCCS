package main.scala.ast

trait Eval[A] {
    def eval: Option[A]
}
