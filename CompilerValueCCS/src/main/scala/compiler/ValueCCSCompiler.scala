package main.scala.compiler

import main.scala.ast.ValueCCS
import main.scala.parser._

import org.antlr.v4.runtime._

object ValueCCSCompiler:

  def apply(input: String): ValueCCS =
    val lexer = new CcsvpLexer(CharStreams.fromString(input))
    val tokens = new CommonTokenStream(lexer)
    val parser = new CcsvpParser(tokens)

    val ccsvpVisitor = ValueCCSParser
    ccsvpVisitor.visit(parser.program())
