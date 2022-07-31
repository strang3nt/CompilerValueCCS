package main.scala.parser

import main.scala.ast._
import main.scala.ast.Aexpr._
import main.scala.ast.Aexpr.Factor._
import main.scala.ast.Bexpr._
import main.scala.ast.Bexpr.BoolOperator._
import main.scala.ast.Bexpr.BoolTerm._
import main.scala.ast.CommonAst._
import main.scala.eval.EvalAexpr.eval as evalA
import main.scala.eval.EvalBexpr.eval as evalB
import main.scala.parser._

import org.antlr.v4.runtime._
import main.scala.compiler.TestParser

class ValueCCSParserSuite extends munit.FunSuite {

  def returnCcsvpAst(str: String): ValueCCS =
    val (parser, ccsvpVisitor) = TestParser(str)
    ccsvpVisitor.visit(parser.ccsvp)

  test("aexpr") {
    val (parser, ccsvpVisitor) = TestParser("5+24*3-4")
    val ast = ccsvpVisitor.visit(parser.expr)
    assertEquals(
      ast,
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
    val ast = returnCcsvpAst("Kr(x)")
    assertEquals(
      ast,
      Const(
        "Kr",
        Some(List(Expr(Term(Factor.ID(Variable("x")), List.empty), List.empty)))
      )
    )
  }

  test("inputch") {
    val ast = returnCcsvpAst("kr(x).K")
    assertEquals(
      ast,
      InputCh(Channel("kr"), Some(Variable("x")), Const("K", None))
    )
  }

  test("tauch") {
    val ast = returnCcsvpAst("tau.K")
    assertEquals(
      ast,
      TauCh(Const("K", None))
    )
  }

  test("outputch") {
    val ast = returnCcsvpAst("'kr(1).K")
    assertEquals(
      ast,
      OutputCh(
        Channel("kr"),
        Some(Expr(Term(Factor.NUMBER(Natural(1)), List.empty), List.empty)),
        Const("K", None)
      )
    )
  }

  test("ifthen") {
    val ast = returnCcsvpAst("if 1 > 2 then K")
    assertEquals(
      ast,
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
        Const("K", None)
      )
    )
  }

  test("par") {
    val ast = returnCcsvpAst("K | K")
    assertEquals(
      ast,
      Par(Const("K", None), Const("K", None))
    )
  }

  test("sum") {
    val ast = returnCcsvpAst("K + K")
    assertEquals(
      ast,
      Sum(List(Const("K", None), Const("K", None)))
    )
  }

  test("restrict") {
    val ast = returnCcsvpAst("K \\ {y}")
    assertEquals(
      ast,
      Restrict(Const("K", None), List(Channel("y")))
    )
  }

  test("valueCCS") {
    val ast = returnCcsvpAst("'kr(x).K|kw(y).K(y)")
    assertEquals(
      ast,
      Par(
        OutputCh(
          Channel("kr"),
          Some(Expr(Term(ID(Variable("x")), Nil), Nil)),
          Const("K", None)
        ),
        InputCh(Channel("kw"),Some(Variable("y")),Const("K",Some(List(Expr(Term(ID(Variable("y")),Nil),Nil)))))
      )
    )
  }
}
