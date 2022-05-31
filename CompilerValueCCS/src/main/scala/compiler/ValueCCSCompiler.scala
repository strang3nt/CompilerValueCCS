package main.scala.compiler

import main.scala.ast.PureCCSAst._
import main.scala.ast.ValueCCSAst._
import main.scala.parser.ValueCCSParser

import main.scala.process.CCSSystem._
import main.scala.process.ValueCCSProcess

object ValueCCSCompiler {

  def compile(system: ValueCCSSystem): String = 
    system.l
      .map(p => translateProcess(p))
      .mkString("\n")

  private def translateProcess(src: ValueCCSProcess): PureCCSSystem = ???

}
