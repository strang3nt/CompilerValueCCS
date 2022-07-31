package main.scala.compiler

import main.scala.parser._
import main.scala.ast._
import main.scala.ast.CommonAst._
import main.scala.ast.Aexpr._
import main.scala.ast.Aexpr.Factor._

import org.antlr.v4.runtime._

object TestParser:

  def apply(input: String): (CcsvpParser, ValueCCSParser.type) =
    val lexer = new CcsvpLexer(CharStreams.fromString(input))
    val tokens = new CommonTokenStream(lexer)
    val parser = new CcsvpParser(tokens)

    val ccsvpVisitor = ValueCCSParser
    (parser, ccsvpVisitor)

class ValueCCSCompilerSuite extends munit.FunSuite:
  
  test("Program: K(x) = 'kr(x).K + kw(y).K(y)") {
    val progr = "K(x) = 'kr(x).K + kw(y).K(y)"
    val (parser, ccsvpVisitor) = TestParser(progr)
    assertEquals(ccsvpVisitor.visit(parser.program),
      ValueCCSProgram(Constant("K", Some(List(Variable("x")))),
      Sum(List(
        OutputCh(
          Channel("kr"),
          Some(Expr(Term(ID(Variable("x")), Nil), Nil)),
          Const("K", None)
        ),
        InputCh(Channel("kw"),Some(Variable("y")),Const("K",Some(List(Expr(Term(ID(Variable("y")),Nil),Nil)))))
      ))))
  }
