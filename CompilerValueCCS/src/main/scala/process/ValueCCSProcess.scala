package main.scala.process

import main.scala.ast.ValueCCSAst.{ ValueCCS }

final case class ValueCCSProcess(name: String, process: ValueCCS):

  override def toString() =
    name ++ " = " ++ process.toString
