package main.scala.parser

import main.scala.parser.ValueCCSParser._
import main.scala.eval.EvalAexpr.{eval => evalA}
import main.scala.eval.EvalBexpr.{eval => evalB}
import main.scala.ast.Naturals.Natural
import main.scala.lexer._
import main.scala.ast.Aexpr._
import main.scala.ast.Aexpr.Factor._
import main.scala.ast.ValueCCSAst._
import main.scala.ast.ValueCCSAst.ValueCCS._
import main.scala.ast.CommonAst._
import main.scala.ast.Bexpr._
import main.scala.ast.Bexpr.BoolOperator._
import main.scala.ast.Bexpr.BoolTerm._

class ValueCCSParserSuite extends munit.FunSuite {
  test("aexpr") {

    val ast = for {
      tokens <- CCSLexer("5+24*3-4").right
      ast <- ValueCCSParser(tokens, phrase(ValueCCSParser.aexpr)).right
    } yield ast
    assertEquals(
      ast.getOrElse("Mistake"),
      Expr(
        Term(NUMBER(Natural(5)), List.empty),
        List(
          (Add, Term(NUMBER(Natural(24)), List((Mul, NUMBER(Natural(3)))))),
          (Sub, Term(NUMBER(Natural(4)), List.empty))
        )
      )
    )
  }

  test("constant") {
    val ast = for {
      tokens <- CCSLexer("Kr(x)").right
      ast <- ValueCCSParser(tokens, ValueCCSParser.constant).right
    } yield ast
    println(ast)
    assertEquals(
      ast.getOrElse(""),
      Constant(
        "Kr",
        Some(List(Expr(Term(Factor.ID(Variable("x")), List.empty), List.empty)))
      )
    )
  }

  test("inputch") {
    val ast = for {
      tokens <- CCSLexer("kr(x).K").right
      ast <- ValueCCSParser(tokens, ValueCCSParser.inputCh).right
    } yield ast
    println(ast)
    assertEquals(
      ast.getOrElse("Error"),
      InputCh(Channel("kr"), Some(Variable("x")), Constant("K", None))
    )
  }

  test("tauch") {
    val ast = for {
      tokens <- CCSLexer("tau.K").right
      ast <- ValueCCSParser(tokens, ValueCCSParser.tauCh).right
    } yield ast
    println(ast)
    assertEquals(
      ast.getOrElse("Error"),
      InputCh(Tau(), None, Constant("K", None))
    )
  }

  test("outputch") {
    val ast = for {
      tokens <- CCSLexer("'kr(1).K").right
      ast <- ValueCCSParser(tokens, phrase(ValueCCSParser.outputCh)).right
    } yield ast
    println(ast)
    assertEquals(
      ast.getOrElse("Error"),
      OutputCh(
        Channel("kr"),
        Some(Expr(Term(Factor.NUMBER(Natural(1)), List.empty), List.empty)),
        Constant("K", None)
      )
    )
  }

  test("ifthen") {
    val ast = for {
      tokens <- CCSLexer("if (1 > 2) then K").right
      ast <- ValueCCSParser(tokens, ValueCCSParser.ifThen).right
    } yield ast
    println(ast)
    assertEquals(
      ast.getOrElse("Error"),
      IfThen(
        BoolBinOp(
          BoolExpr(
            ExprBinOp(
              Expr(Term(NUMBER(Natural(1)), Nil), Nil),
              Ge,
              Expr(Term(NUMBER(Natural(2)), Nil), Nil)
            )
          ),
          Nil
        ),
        Constant("K", None)
      )
    )
  }

  test("par") {
    val ast = for {
      tokens <- CCSLexer("K | K").right
      ast <- ValueCCSParser(tokens, ValueCCSParser.par).right
    } yield ast
    println(ast)
    assertEquals(
      ast.getOrElse("Error"),
      Par(Constant("K", None), Constant("K", None))
    )
  }

  test("sum") {
    val ast = for {
      tokens <- CCSLexer("K + K").right
      ast <- ValueCCSParser(tokens, ValueCCSParser.sum).right
    } yield ast
    println(ast)
    assertEquals(
      ast.getOrElse("Error"),
      Sum(List(Constant("K", None), Constant("K", None)))
    )
  }

  test("restrict") {
    val ast = for {
      tokens <- CCSLexer("K\\{y}").right
      ast <- ValueCCSParser(tokens, ValueCCSParser.restrict).right
    } yield ast
    println(ast)
    assertEquals(
      ast.getOrElse("Error"),
      Restrict(Constant("K", None), List(Channel("y")))
    )
  }

  test("valueCCS") {
    val ast = for {
      tokens <- CCSLexer("'kr(x).K|kw(y).K(y)").right
      _ = println(tokens)
      ast <- ValueCCSParser(tokens, ValueCCSParser.program).right
    } yield ast
    println(ast)
    println(ast.getOrElse("Error"))
    assertEquals(
      ast.getOrElse("Error"),
      Par(
        OutputCh(
          Channel("kr"),
          Some(Expr(Term(ID(Variable("x")), Nil), Nil)),
          Constant("K", None)
        ),
        InputCh(Channel("kw"),Some(Variable("y")),Constant("K",Some(List(Expr(Term(ID(Variable("y")),Nil),Nil)))))
      )
    )
  }
}
