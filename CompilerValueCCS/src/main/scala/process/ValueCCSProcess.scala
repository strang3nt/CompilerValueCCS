package main.scala.process

import main.scala.ast.CommonAst.Variable
import main.scala.ast.ValueCCS

import scala.util.parsing.input.Positional

final case class Constant(name: String, l: Option[List[Variable]])
    extends Positional:
  override def toString: String =
    val vs =
      l match
        case Some(vs) => "(" + vs.mkString(", ") + ")"
        case None     => ""
    name + vs

final case class ValueCCSProcess(name: Constant, process: ValueCCS)
    extends Positional:
  override def toString: String =
    name.toString ++ " = " ++ process.toString
