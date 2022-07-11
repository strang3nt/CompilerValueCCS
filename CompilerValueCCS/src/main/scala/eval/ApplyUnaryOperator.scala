package main.scala.eval

trait ApplyUnaryOperator[Input, Output] {
  def applyOperator(x: Input): Output
}
