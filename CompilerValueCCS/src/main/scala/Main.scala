package main.scala

import main.scala.ast.Aexpr._
import main.scala.ast._
import main.scala.parser.ValueCCSParser
import main.scala.parser.ValueCCSParser.program
import main.scala.compiler._
import main.scala.compiler.ValueCCSCompilationError._

object Main extends App:

  println("Insert value passing CCS program")
  val v = scala.io.StdIn.readLine()
  val proc = ValueCCSCompiler(v)
  println("Insert size of number set")
  val s = scala.io.StdIn.readInt()

  proc match
    case Right(valueCCS) => 
      println(PureCCSCompiler(valueCCS, s).mkString("/n"))
    case Left(ValueCCSParserError(Location(_, y), msg) ) =>
      throw new Exception(s"Parsing error at column {y} with message: {msg}")
    case Left(ValueCCSLexerError(Location(_, y), msg)) =>
      throw new Exception(s"Lexer error at column {y} with message: {msg}")
