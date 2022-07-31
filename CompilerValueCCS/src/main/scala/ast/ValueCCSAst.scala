package main.scala.ast

import main.scala.ast.Aexpr.Aexpr
import main.scala.ast.Bexpr.Bexpr
import main.scala.ast.CommonAst._

case class Const(name: String, l: Option[List[Aexpr]]) extends ValueCCS
case class TauCh(p: ValueCCS) extends ValueCCS
case class InputCh(c: Channel, v: Option[Variable], p: ValueCCS)
    extends ValueCCS
case class OutputCh(c: Channel, e: Option[Aexpr], p: ValueCCS) extends ValueCCS
case class IfThen(b: Bexpr, p: ValueCCS) extends ValueCCS
case class Par(left: ValueCCS, right: ValueCCS) extends ValueCCS
case class Sum(l: List[ValueCCS]) extends ValueCCS
case class Restrict(p: ValueCCS, l: List[Channel]) extends ValueCCS
case class Redirection(p: ValueCCS, cs: List[(Channel, Channel)])
    extends ValueCCS
