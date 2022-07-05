package main.scala.parser

class CCSLexerSuite extends munit.FunSuite {
  test("lexer") {
      val valueCCSstring = "K(x)='kr(x) | kc"
      val aexprString = "5+2*3-4"
      for {
        tokens0 <- ValueCCSLexer(valueCCSstring).right
      }

      assertEquals(
        tokens0, 
        List(IDENTIFIER("K"), LBRACKET, IDENTIFIER("x"), RBRACKET,
          DEF, OUT, IDENTIFIER("kr"), LBRACKET, IDENTIFIER("x"), RBRACKET,
          PAR, IDENTIFIER("kc")))
      for {
        tokens1 <- ValueCCSLexer(aexprString).right
      }
      assertEquals(
        tokens1,
        List(INTEGER(5), SUM, INTEGER(2), MUL, INTEGER(3), SUB, INTEGER(4))
      )
    }

}