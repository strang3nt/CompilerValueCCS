package main.scala.valueccsparser

import grammar._
import ValueCCSProcess._

final case class ValueCCS(name: String, process: ValueCCSProcess):

  override def toString() =
    name ++ " = " ++ process.toString
