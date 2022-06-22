package main.scala.process

import main.scala.ast.ValueCCSAst.{ ValueCCS }
import main.scala.ast.CommonAst.Variable

final case class Constant(name: String, l: Option[List[Variable]])

final case class ValueCCSProcess(name: Constant, process: ValueCCS):
  override def toString: String =
    name.toString ++ " = " ++ process.toString
