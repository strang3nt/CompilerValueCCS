package main.scala.ast

object CommonAst:

  final case class Channel(name: String):
    override def toString: String = name

  final case class Tau():
    override def toString: String = "tau"

  final case class Variable(name: String):
    override def toString: String = name
