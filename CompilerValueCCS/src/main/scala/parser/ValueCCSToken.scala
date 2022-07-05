package main.scala.parser

import scala.util.parsing.input.Positional

sealed trait ValueCCSToken extends Positional
case class IDENTIFIER(str: String) extends ValueCCSToken
case class INTEGER(num: Int) extends ValueCCSToken
case object DEF extends ValueCCSToken // =
case object SEPARATOR extends ValueCCSToken // .
case object OUT extends ValueCCSToken // '
case object COMMA extends ValueCCSToken
case object LBRACKET extends ValueCCSToken
case object RBRACKET extends ValueCCSToken
case object CURLY_LBRACKET extends ValueCCSToken
case object CURLY_RBRACKET extends ValueCCSToken
case object SUM extends ValueCCSToken
case object SUB extends ValueCCSToken
case object MUL extends ValueCCSToken
case object DIV extends ValueCCSToken
case object LEQ extends ValueCCSToken
case object LE extends ValueCCSToken
case object GEQ extends ValueCCSToken
case object GE extends ValueCCSToken
case object EQUALS extends ValueCCSToken // ==
case object NOT extends ValueCCSToken
case object AND extends ValueCCSToken
case object OR extends ValueCCSToken
case object PAR extends ValueCCSToken // |
case object IF extends ValueCCSToken
case object THEN extends ValueCCSToken
case object RESTR extends ValueCCSToken // \
case object TAU extends ValueCCSToken
