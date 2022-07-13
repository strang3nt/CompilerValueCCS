package main.scala.compiler

import main.scala.ast.ValueCCS
import main.scala.parser.ValueCCSLexer
import main.scala.parser.ValueCCSParser
import main.scala.process.ValueCCSProcess

object ValueCCSCompiler:
  def apply(code: String): Either[ValueCCSCompilationError, ValueCCSProcess] = {
    for {
      tokens <- ValueCCSLexer(code)
      ast <- ValueCCSParser(tokens, ValueCCSParser.program)
    } yield ast
  }
