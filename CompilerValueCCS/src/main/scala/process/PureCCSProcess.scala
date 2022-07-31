package main.scala.process

import main.scala.ast.PureCCS

final case class PureCCSProgram(name: String, process: PureCCS):
  override def toString() =
    this.name ++ " = " ++ process.toString
