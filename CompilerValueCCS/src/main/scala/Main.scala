package main.scala

import main.scala.parser.ValueCCSParser._
import main.scala.ast.ValueCCSAst._
import Aexpr._
import Natural._
import ExprOperator._

object Main extends App {
  println(parse("Porta(1,2,3,4)"))

  val expr = BinOp(
    Var(Variable("x")), 
    Add, 
    BinOp(Num(One), Sub, Num(Five)))
  
  println(s"Prettified expression: ${expr}")

}
