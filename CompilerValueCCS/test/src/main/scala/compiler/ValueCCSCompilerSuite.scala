package main.scala.compiler

import main.scala.parser.{CCSLexer, ValueCCSParser}

class ValueCCSCompilerSuite extends munit.FunSuite:
  test("Program: K(x) = 'kr(x).K | kw(y).K(y)") {
    val ast = for {
      tokens <- CCSLexer("K(x) = 'kr(x).K | kw(y).K(y)").right
      ast <- ValueCCSParser(tokens, ValueCCSParser.program).right
    } yield ast
    ast match {
      case Right(valid) => 
        println(valid); println(ValueCCSCompiler.compile(valid, 10))
      case Left(notValid) => fail(s"Could not parse grammar: {notValid}")
    }
  }
