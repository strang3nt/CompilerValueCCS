package main.scala.parser

import main.scala.parser.ValueCCSParser._
import main.scala.ast.ValueCCSAst._
import main.scala.eval.EvalAexpr.{ eval => evalA }
import main.scala.eval.EvalBexpr.{ eval => evalB }
import main.scala.ast.Naturals.Natural
import main.scala.lexer.CCSToken
import main.scala.lexer.CCSLexer
import main.scala.ast.Aexpr.Aexpr
import main.scala.ast.Aexpr.Factor
import main.scala.ast.CommonAst.Variable
import main.scala.ast.Aexpr.Expr
import main.scala.ast.Aexpr.Term
import Factor._
import main.scala.ast.Aexpr.Add
import main.scala.ast.Aexpr.Sub
import main.scala.ast.Aexpr.Mul
import main.scala.ast.ValueCCSAst._
import ValueCCS._
import main.scala.ast.CommonAst.Channel
import main.scala.ast.CommonAst.Tau
import main.scala.ast.Bexpr.ExprBinOp
import main.scala.ast.Bexpr.BoolBinOp
import main.scala.ast.Bexpr.BoolOperator._
import main.scala.ast.Bexpr.BoolTerm._



class ValueCCSParserSuite extends munit.FunSuite {
  test("aexpr") {

    val ast = for {
        tokens <- CCSLexer("5+24*3-4").right
        ast <- ValueCCSParser(tokens, ValueCCSParser.aexpr).right
      } yield ast
    assertEquals(ast.getOrElse("Mistake"), 
      Expr(
        Term(NUMBER(Natural(5)), List.empty),
        List(
          (Add, Term( NUMBER(Natural(24)), List( (Mul, NUMBER(Natural(3))) )) ),
          (Sub, Term( NUMBER(Natural(4)), List.empty)))
      ))
  }

  test("constant") {
    val ast = for {
        tokens <- CCSLexer("kr(x)").right
        ast <- ValueCCSParser(tokens, ValueCCSParser.constant).right
      } yield ast
    println(ast)
    assertEquals(ast.getOrElse(""), Constant("kr", Some(List(Expr(Term(Factor.ID(Variable("x")), List.empty), List.empty)))))
  }

  test("inputch") {
    val ast = for {
        tokens <- CCSLexer("kr(x).k").right
        ast <- ValueCCSParser(tokens, ValueCCSParser.inputCh).right
      } yield ast
    println(ast)
    assertEquals(ast.getOrElse("Error"), InputCh(Channel("kr"), Some(Variable("x")), Constant("k", None)))
  }

  test("tauch") {
    val ast = for {
        tokens <- CCSLexer("tau.k").right
        ast <- ValueCCSParser(tokens, ValueCCSParser.tauCh).right
      } yield ast
    println(ast)
    assertEquals(ast.getOrElse("Error"), InputCh(Tau(), None, Constant("k", None)))
  }

  test("outputch") {
    val ast = for {
        tokens <- CCSLexer("'kr(1).k").right
        ast <- ValueCCSParser(tokens, phrase(ValueCCSParser.outputCh)).right
      } yield ast
    println(ast)
    assertEquals(ast.getOrElse("Error"), OutputCh(Channel("kr"), Some(Expr(Term(Factor.NUMBER(Natural(1)), List.empty), List.empty)), Constant("k", None)))
  }

  test("ifthen") {
    val ast = for {
        tokens <- CCSLexer("if 1 > 2 then k").right
        ast <- ValueCCSParser(tokens, ValueCCSParser.ifThen).right
      } yield ast
    println(ast)
    assertEquals(ast.getOrElse("Error"), IfThen(BoolBinOp(BoolExpr(ExprBinOp(Expr(Term(NUMBER(Natural(1)), Nil), Nil), Ge, Expr(Term(NUMBER(Natural(2)), Nil), Nil))), Nil), Constant("k", None)))
  }

  test("par") {
    val ast = for {
        tokens <- CCSLexer("k|k").right
        _ = println(tokens)
        ast <- ValueCCSParser(tokens, ValueCCSParser.par).right
      } yield ast
    println(ast)
    assertEquals(ast.getOrElse("Error"), Par(Constant("k", None), Constant("k", None)))
  }

  test("sum") {
    val ast = for {
        tokens <- CCSLexer("k+k").right
        _ = println(tokens)
        ast <- ValueCCSParser(tokens, ValueCCSParser.sum).right
      } yield ast
    println(ast)
    assertEquals(ast.getOrElse("Error"), Sum(List (Constant("k", None), Constant("k", None))))
  }

  test("restrict") {
    val ast = for {
        tokens <- CCSLexer("k\\{y}").right
        _ = println(tokens)
        ast <- ValueCCSParser(tokens, ValueCCSParser.restrict).right
      } yield ast
    println(ast)
    assertEquals(ast.getOrElse("Error"), Restrict(Constant("k", None), List(Channel("y"))))
  }

  test("valueCCS") {
    val ast = for {
        tokens <- CCSLexer("'kr(x).k+kw(y).k(y)").right
        _ = println(tokens)
        ast <- ValueCCSParser(tokens, ValueCCSParser.program).right
      } yield ast
    println(ast)
    assertEquals(true, false)
  }
}
