package main.scala

import main.scala.parser.ValueCCSParser._
import main.scala.ast.ValueCCSAst._
import main.scala.ast.Aexpr._
import Natural._

object Main extends App {
  println(parse("Porta(1*(3+2)-1,2,3,4)"))
}
