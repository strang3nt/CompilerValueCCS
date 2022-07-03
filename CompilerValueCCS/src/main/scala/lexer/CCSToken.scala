package main.scala.lexer

import scala.util.parsing.input.Positional

sealed trait CCSToken extends Positional
case class IDENTIFIER(str: String) extends CCSToken
case class INTEGER(num: Int) extends CCSToken
case object DEF extends CCSToken // =
case object SEPARATOR extends CCSToken // .
case object OUT extends CCSToken // '
case object COMMA extends CCSToken
case object LBRACKET extends CCSToken
case object RBRACKET extends CCSToken
case object CURLY_LBRACKET extends CCSToken
case object CURLY_RBRACKET extends CCSToken
case object SUM extends CCSToken
case object SUB extends CCSToken
case object MUL extends CCSToken
case object DIV extends CCSToken
case object LEQ extends CCSToken
case object LE extends CCSToken
case object GEQ extends CCSToken
case object GE extends CCSToken
case object EQUALS extends CCSToken // ==
case object NOT extends CCSToken
case object AND extends CCSToken
case object OR extends CCSToken
case object PAR extends CCSToken // |
case object IF extends CCSToken
case object THEN extends CCSToken
case object RESTR extends CCSToken // \
case object TAU extends CCSToken
