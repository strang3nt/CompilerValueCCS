package main.scala.eval

trait ApplyOperator[Input, Output]:
  def applyOperator(x: Input, y: Input): Output
