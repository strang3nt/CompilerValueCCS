package main.scala.process

import main.scala.ast.PureCCSAst.{PureCCS}

final case class PureCCSProcess(name: String, process: PureCCS):
  override def toString() =
    this.name ++ " = " ++ process.toString
