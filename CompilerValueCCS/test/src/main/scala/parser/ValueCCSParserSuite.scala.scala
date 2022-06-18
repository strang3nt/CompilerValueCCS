package main.scala.parser

import main.scala.parser.ValueCCSParser._
import main.scala.ast.ValueCCSAst._
import main.scala.eval.EvalAexpr.{ eval => evalA }
import main.scala.eval.EvalBexpr.{ eval => evalB }


class ValueCCSParserSuite extends munit.FunSuite {
  test("aexpr") {
    import main.scala.ast.Aexpr._
    import Natural._

    val obtained: Aexpr = aexpr.parse("5+2*3-4") match {
      case Right(_, x) => x
      case Left(_) => fail("Parsing shouldn't fail")
    }

    assertEquals(obtained.toString, "5 + 2 * 3 - 4")
    assertEquals(evalA(obtained), 7)
  }
  test("bexpr") {
    import main.scala.ast.Bexpr._

    val obtained1: Bexpr = bexpr.parse("5<4") match {
      case Right(_, x) => x
      case Left(_) => fail("Parsing shouldn't fail")
    }
    val obtained2: Bexpr = bexpr.parse("5<4&&3<2") match {
      case Right(_, x) => x
      case Left(_) => fail("Parsing shouldn't fail")
    }
    val obtained3: Bexpr = bexpr.parse("!(5<4)&&3<2") match {
      case Right(_, x) => x
      case Left(_) => fail("Parsing shouldn't fail")
    }

    assertEquals(obtained1.toString, "5 < 4")

    assertEquals(obtained2.toString, "5 < 4 && 3 < 2")
    assertEquals(evalB(obtained2), false)

    assertEquals(obtained3.toString, "!(5 < 4) && 3 < 2")
    assertEquals(evalB(obtained3), false)

    //assertEquals(obtained.eval(), Some(7))
  }
}