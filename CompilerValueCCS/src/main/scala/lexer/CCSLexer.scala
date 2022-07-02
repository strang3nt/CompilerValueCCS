package main.scala.lexer

import scala.util.parsing.combinator.RegexParsers
import scala.util.parsing.combinator.JavaTokenParsers

import CCSToken._

object CCSLexer extends RegexParsers:

  override def skipWhitespace: Boolean = true
  override val whiteSpace = "[ \\s]+".r

  def apply(code: String): Either[((Int, Int), String), List[CCSToken]] = {
    parse(tokens, code) match {
      case NoSuccess(msg, next)  => Left((next.pos.line, next.pos.column), msg)
      case Success(result, next) => Right(result)
      case err @ _ => throw new Exception(s"CCSLexer: Fatal: $err")
    }
  }

  def tokens: Parser[List[CCSToken]] = {
    phrase(
      rep1(
        integer | equals | separator | comma | out | lbracket |
          rbracket | sum | sub | mul | div | leq | le | geq | ge | define | not | and |
          or | par | ifStatement | thenStatement | restr | tau | identifier | lcbracket | rcbracket
      )
    )
  }

  def identifier: Parser[IDENTIFIER] = positioned {
    "[a-zA-Z_][a-zA-Z0-9_]*".r ^^ (IDENTIFIER(_))
  }

  def integer: Parser[INTEGER] = positioned {
    """[1-9]\d*""".r ^^ (i => INTEGER(i.toInt))
  }
  def define: Parser[DEF] = positioned { "=" ^^^ DEF() }
  def separator: Parser[SEPARATOR] = positioned { "." ^^^ SEPARATOR() }
  def comma: Parser[COMMA] = positioned { "," ^^^ COMMA() }
  def out: Parser[OUT] = positioned { "'" ^^^ OUT() }
  def lbracket: Parser[LBRACKET] = positioned { "(" ^^^ LBRACKET() }
  def rbracket: Parser[RBRACKET] = positioned { ")" ^^^ RBRACKET() }
  def lcbracket: Parser[LCBRACKET] = positioned { "{" ^^^ LCBRACKET() }
  def rcbracket: Parser[RCBRACKET] = positioned { "}" ^^^ RCBRACKET() }

  def sum: Parser[SUM] = positioned { "+" ^^^ SUM() }
  def sub: Parser[SUB] = positioned { "-" ^^^ SUB() }
  def mul: Parser[MUL] = positioned { "*" ^^^ MUL() }
  def div: Parser[DIV] = positioned { "/" ^^^ DIV() }

  def leq: Parser[LEQ] = positioned { "<=" ^^^ LEQ() }
  def le: Parser[LE] = positioned { "<" ^^^ LE() }
  def geq: Parser[GEQ] = positioned { ">=" ^^^ GEQ() }
  def ge: Parser[GE] = positioned { ">" ^^^ GE() }
  def equals: Parser[EQUALS] = positioned { "==" ^^^ EQUALS() }
  def not: Parser[NOT] = positioned { "!" ^^^ NOT() }
  def and: Parser[AND] = positioned { "&&" ^^^ AND() }
  def or: Parser[OR] = positioned { "||" ^^^ OR() }

  def par: Parser[PAR] = positioned { "|" ^^^ PAR() }
  def ifStatement: Parser[IF] = positioned { "if" ^^^ IF() }
  def thenStatement: Parser[THEN] = positioned { "then" ^^^ THEN() }
  def restr: Parser[RESTR] = positioned { "\\" ^^^ RESTR() }
  def tau: Parser[TAU] = positioned { "tau" ^^ (_ => TAU()) }
