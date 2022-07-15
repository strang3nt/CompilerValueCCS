package main.scala.parser

import main.scala.ast._
import main.scala.ast.Aexpr._
import main.scala.ast.Aexpr.Factor._
import main.scala.ast.Bexpr._
import main.scala.ast.Bexpr.BoolOperator._
import main.scala.ast.Bexpr.BoolTerm._
import main.scala.ast.CommonAst._
import main.scala.ast.ValueCCS._
import main.scala.eval.EvalAexpr.eval as evalA
import main.scala.eval.EvalBexpr.eval as evalB
import main.scala.parser._
import main.scala.parser.ValueCCSParser._

class ValueCCSParserSuite extends munit.FunSuite {
  test("aexpr") {

    val ast = for {
      tokens <- ValueCCSLexer("5+24*3-4")
      ast <- ValueCCSParser(tokens, phrase(ValueCCSParser.aexpr))
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
      tokens <- ValueCCSLexer("Kr(x)")
      ast <- ValueCCSParser(tokens, ValueCCSParser.constant)
    } yield ast
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
      tokens <- ValueCCSLexer("kr(x).K")
      ast <- ValueCCSParser(tokens, ValueCCSParser.inputCh)
    } yield ast
    assertEquals(
      ast.getOrElse("Error"),
      InputCh(Channel("kr"), Some(Variable("x")), Constant("K", None))
    )
  }

  test("tauch") {
    val ast = for {
      tokens <- ValueCCSLexer("tau.K")
      ast <- ValueCCSParser(tokens, ValueCCSParser.tauCh)
    } yield ast
    assertEquals(
      ast.getOrElse("Error"),
      TauCh(Constant("K", None))
    )
  }

  test("outputch") {
    val ast = for {
      tokens <- ValueCCSLexer("'kr(1).K")
      ast <- ValueCCSParser(tokens, phrase(ValueCCSParser.outputCh))
    } yield ast
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
      tokens <- ValueCCSLexer("if 1 > 2 then K")
      ast <- ValueCCSParser(tokens, ValueCCSParser.ifThen)
    } yield ast
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
      tokens <- ValueCCSLexer("K | K")
      ast <- ValueCCSParser(tokens, ValueCCSParser.par)
    } yield ast
    assertEquals(
      ast.getOrElse("Error"),
      Par(Constant("K", None), Constant("K", None))
    )
  }

  test("sum") {
    val ast = for {
      tokens <- ValueCCSLexer("K + K")
      ast <- ValueCCSParser(tokens, ValueCCSParser.sum)
    } yield ast
    assertEquals(
      ast.getOrElse("Error"),
      Sum(List(Constant("K", None), Constant("K", None)))
    )
  }

  test("restrict") {
    val ast = for {
      tokens <- ValueCCSLexer("K \\ {y}")
      ast <- ValueCCSParser(tokens, ValueCCSParser.restrict)
    } yield ast
    assertEquals(
      ast.getOrElse("Error"),
      Restrict(Constant("K", None), List(Channel("y")))
    )
  }

  test("valueCCS") {
    val ast = for {
      tokens <- ValueCCSLexer("'kr(x).K|kw(y).K(y)")
      ast <- ValueCCSParser(tokens, ValueCCSParser.valueCCS)
    } yield ast
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
