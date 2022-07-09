package main.scala.parser

import scala.util.parsing.combinator.{JavaTokenParsers, RegexParsers}
import main.scala.compiler.ValueCCSCompilationError.ValueCCSLexerError
import main.scala.compiler.Location

object ValueCCSLexer extends RegexParsers:

  override def skipWhitespace: Boolean = true
  override val whiteSpace = "[ \\s]+".r

  def apply(code: String): Either[ValueCCSLexerError, List[ValueCCSToken]] = {
    parse(tokens, code) match {
      case NoSuccess(msg, next)  => Left(ValueCCSLexerError(Location(next.pos.line, next.pos.column), msg))
      case Success(result, next) => Right(result)
      case err @ _ => throw new Exception(s"CCSLexer: Fatal: $err")
    }
  }

  def tokens: Parser[List[ValueCCSToken]] = {
    phrase(
      rep1(
        integer | equals | separator | comma | out | lbracket |
        rbracket | sum | sub | mul | div | leq | le | geq | ge | define | not | and |
        or | par | ifStatement | thenStatement | restr | tau | identifier | curly_lbracket | curly_rbracket |
        squared_lbracket | squared_rbracket
      )
    )
  }

  def identifier: Parser[IDENTIFIER] = positioned {
    "[a-zA-Z_][a-zA-Z0-9_]*".r ^^ (IDENTIFIER(_))
  }
  def integer: Parser[INTEGER] = positioned {
    """[1-9]\d*""".r ^^ (i => INTEGER(i.toInt))
  }
  def define: Parser[DEF.type] = positioned { "=" ^^^ DEF }
  def separator: Parser[SEPARATOR.type] = positioned { "." ^^^ SEPARATOR }
  def comma: Parser[COMMA.type] = positioned { "," ^^^ COMMA }
  def out: Parser[OUT.type] = positioned { "'" ^^^ OUT }
  def lbracket: Parser[LBRACKET.type] = positioned { "(" ^^^ LBRACKET }
  def rbracket: Parser[RBRACKET.type] = positioned { ")" ^^^ RBRACKET }
  def curly_lbracket: Parser[CURLY_LBRACKET.type] = positioned {
    "{" ^^^ CURLY_LBRACKET
  }
  def curly_rbracket: Parser[CURLY_RBRACKET.type] = positioned {
    "}" ^^^ CURLY_RBRACKET
  }
  def squared_lbracket: Parser[SQUARED_LBRACKET.type] = positioned {
    "[" ^^^ SQUARED_LBRACKET
  }
  def squared_rbracket: Parser[SQUARED_RBRACKET.type] = positioned {
    "]" ^^^ SQUARED_RBRACKET
  }

  def sum: Parser[SUM.type] = positioned { "+" ^^^ SUM }
  def sub: Parser[SUB.type] = positioned { "-" ^^^ SUB }
  def mul: Parser[MUL.type] = positioned { "*" ^^^ MUL }
  def div: Parser[DIV.type] = positioned { "/" ^^^ DIV }

  def leq: Parser[LEQ.type] = positioned { "<=" ^^^ LEQ }
  def le: Parser[LE.type] = positioned { "<" ^^^ LE }
  def geq: Parser[GEQ.type] = positioned { ">=" ^^^ GEQ }
  def ge: Parser[GE.type] = positioned { ">" ^^^ GE }
  def equals: Parser[EQUALS.type] = positioned { "==" ^^^ EQUALS }
  def not: Parser[NOT.type] = positioned { "!" ^^^ NOT }
  def and: Parser[AND.type] = positioned { "&&" ^^^ AND }
  def or: Parser[OR.type] = positioned { "||" ^^^ OR }

  def par: Parser[PAR.type] = positioned { "|" ^^^ PAR }
  def ifStatement: Parser[IF.type] = positioned { "if" ^^^ IF }
  def thenStatement: Parser[THEN.type] = positioned { "then" ^^^ THEN }
  def restr: Parser[RESTR.type] = positioned { "\\" ^^^ RESTR }
  def tau: Parser[TAU.type] = positioned { "tau" ^^ (_ => TAU) }
