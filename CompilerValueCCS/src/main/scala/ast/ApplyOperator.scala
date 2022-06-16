package main.scala.ast

trait ApplyOperator[Input, Output]:
  def applyOperator(x: Input, y: Input): Output = ???
  def applyOperator(x: Input): Output = ???
