package main.scala.compiler

enum ValueCCSCompilationError:
  override def toString: String =
    this match
      case ValueCCSLexerError(l, m) =>
        s"Lexer error at position ${l} with message: ${m}"
      case ValueCCSParserError(l, m) =>
        s"Parser error at column ${l} with message: ${m}"

  case ValueCCSLexerError(location: Location, msg: String)
  case ValueCCSParserError(location: Location, msg: String)

final case class Location(line: Int, column: Int):
  override def toString = s"$line:$column"
