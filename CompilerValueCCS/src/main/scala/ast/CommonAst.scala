package main.scala.ast

import scala.util.parsing.input.Positional

object CommonAst:

  final case class Channel(name: String) extends Positional:
    override def toString: String = name

  final case class Tau() extends Positional:
    override def toString: String = "tau"

  final case class Variable(name: String) extends Positional:
    override def toString: String = name
