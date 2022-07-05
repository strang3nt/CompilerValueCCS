package main.scala.compiler

enum ValueCCSCompilationError:
  case ValueCCSLexerError(location: Location, msg: String)
  case ValueCCSParserError(location: Location, msg: String) 

final case class Location(line: Int, column: Int):
  override def toString = s"$line:$column"
