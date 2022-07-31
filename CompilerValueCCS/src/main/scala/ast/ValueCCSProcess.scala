package main.scala.ast

import main.scala.ast.CommonAst.Variable

trait ValueCCS

final case class Constant(name: String, l: Option[List[Variable]])
    extends ValueCCS:
  override def toString: String =
    val vs =
      l match
        case Some(vs) => "(" + vs.mkString(", ") + ")"
        case None     => ""
    name + vs

final case class ValueCCSProgram(name: Constant, process: ValueCCS)
    extends ValueCCS:
  override def toString: String =
    name.toString ++ " = " ++ process.toString
