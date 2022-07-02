package main.scala.lexer

import scala.util.parsing.input.Positional

enum CCSToken extends Positional:

  case IDENTIFIER(str: String)
  case INTEGER(num: Int)
  case DEF() // =
  case SEPARATOR() // .
  case OUT() // '
  case COMMA()
  case LBRACKET()
  case RBRACKET()
  case LCBRACKET()
  case RCBRACKET()
  case SUM()
  case SUB()
  case MUL()
  case DIV()
  case LEQ()
  case LE()
  case GEQ()
  case GE()
  case EQUALS() // ==
  case NOT()
  case AND()
  case OR()
  case PAR() // |
  case IF()
  case THEN()
  case RESTR() // \
  case TAU()
