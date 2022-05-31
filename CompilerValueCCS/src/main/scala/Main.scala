package main.scala

import main.scala.valueccsparser.ValueCCSParser._
import main.scala.valueccsparser.grammar._
import main.scala.valueccsparser.grammar.Aexpr._
import main.scala.valueccsparser.grammar.Natural._
import main.scala.valueccsparser.grammar.ExprOperator._
import main.scala.valueccsparser.grammar.Variable

object Main extends App {
  println(parse("Porta(1,2,3,4)"))

  val expr = BinOp(
    Var(Variable("x")), 
    Add, 
    BinOp(Num(One), Sub, Num(Five)))
  
  println(s"Prettified expression: ${expr}")

}
