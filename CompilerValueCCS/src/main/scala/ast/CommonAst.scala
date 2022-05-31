package main.scala.ast

object CommonAst:

  final case class Channel (name : String):
    override def toString() = name
