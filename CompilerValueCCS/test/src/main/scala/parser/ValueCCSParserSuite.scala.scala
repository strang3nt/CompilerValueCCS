package main.scala.parser

import main.scala.parser.ValueCCSParser._
import main.scala.ast.ValueCCSAst._
import main.scala.ast.Aexpr._

class ValueCCSParserSuite extends munit.FunSuite {
  test("aexpr") {
    import Natural._

    val obtained: Aexpr = aexpr.parse("5+2*3-4") match {
      case Right(_, x) => x
      case Left(_) => fail("Parsing shouldn't fail")
    }

    assertEquals(obtained.toString, "5 + 2 * 3 - 4")
    assertEquals(obtained.eval(), Some(7))
  }
}