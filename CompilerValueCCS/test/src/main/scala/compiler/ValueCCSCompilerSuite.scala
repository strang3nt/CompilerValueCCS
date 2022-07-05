package main.scala.compiler

import main.scala.parser.ValueCCSLexer
import main.scala.parser.ValueCCSParser

class ValueCCSCompilerSuite extends munit.FunSuite:
  test("Program: K(x) = 'kr(x).K | kw(y).K(y)") {
    val ast = for {
      tokens <- ValueCCSLexer("K(x) = 'kr(x).K | kw(y).K(y)").right
      ast <- ValueCCSParser(tokens, ValueCCSParser.program).right
    } yield ast
    ast match {
      case Right(valid) => 
        println(valid); println(PureCCSCompiler(valid, 10))
      case Left(notValid) => fail(s"Could not parse grammar: {notValid}")
    }
  }
