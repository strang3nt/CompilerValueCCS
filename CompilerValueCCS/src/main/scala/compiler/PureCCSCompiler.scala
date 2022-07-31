package main.scala.compiler

import main.scala.ast.Aexpr._
import main.scala.ast.Bexpr._
import main.scala.ast.CommonAst._
import main.scala.ast.PureCCS as P
import main.scala.ast._
import main.scala.eval.EvalAexpr.eval as evalA
import main.scala.eval.EvalBexpr.eval as evalB
import main.scala.parser.ValueCCSParser
import main.scala.ast.{ValueCCSProgram, Constant as ProcessConstant}
import main.scala.process.PureCCSProgram

import scala.collection.immutable.Map

object PureCCSCompiler:

  def apply(
      program: ValueCCS,
      lowerBound: Int,
      upperInclBound: Int
  ): List[PureCCSProgram] =
    val (name, process) = program match {
      case ValueCCSProgram(name, process) => (name, process)
    }
    val natRange = (lowerBound to upperInclBound).toSet
    name match
      case ProcessConstant(n, Some(l)) =>
        val substCombinations: List[List[Natural]] =
          (List
            .fill(l.length)(natRange))
            .flatten
            .combinations(l.length)
            .toList
            .foldLeft(List.empty)((acc, l) =>
              acc ++ l.map(Natural(_)).permutations.toList
            )
        substCombinations.map((nats) =>
          PureCCSProgram(
            n + "_" + nats.mkString("_"),
            translateProcess(process, natRange, (l zip nats).toMap)
          )
        )
      case ProcessConstant(n, None) =>
        PureCCSProgram(n, translateProcess(process, natRange, Map.empty)) :: Nil

  private def translateProcess(
      src: ValueCCS,
      natRange: Set[Int],
      subst: Map[Variable, Natural]
  ): P =
    src match
      case Const(n, None) => P.Constant(n)

      case Const(n, Some(es)) =>
        P.Constant(
          n + "_"
            + es.map(e => evalA(e, subst)).mkString("_")
        )

      case InputCh(Channel(n), Some(v), p) =>
        P.Sum(
          natRange
            .map(i =>
              P.InputCh(
                Channel(n + s"_$i"),
                translateProcess(p, natRange, subst + (v -> Natural(i)))
              )
            )
            .toList
        )

      case InputCh(Channel(n), None, p) =>
        P.InputCh(Channel(n), translateProcess(p, natRange, subst))

      case TauCh(p) =>
        P.TauCh(translateProcess(p, natRange, subst))

      case OutputCh(Channel(n), Some(e), p) =>
        val r = evalA(e, subst)
        P.OutputCh(Channel(n + s"_$r"), translateProcess(p, natRange, subst))

      case OutputCh(Channel(n), None, p) =>
        P.OutputCh(Channel(n), translateProcess(p, natRange, subst))

      case Sum(l) if l.isEmpty =>
        P.Sum(List.empty)

      case Sum(l) =>
        P.Sum(l.map(p => translateProcess(p, natRange, subst)))

      case Par(left, right) =>
        P.Par(
          translateProcess(left, natRange, subst),
          translateProcess(right, natRange, subst)
        )

      case Restrict(p, l) =>
        P.Restrict(
          translateProcess(p, natRange, subst),
          l.foldLeft(List.empty) { case (acc, Channel(n)) =>
            acc ++ natRange.map(i => Channel(n + s"_$i"))
          }
        )

      case Redirection(p, cs) =>
        P.Redirection(
          translateProcess(p, natRange, subst),
          cs.foldLeft(List.empty) { case (acc, (Channel(n), Channel(v))) =>
            acc ++ natRange.map(i => (Channel(n + s"_$i"), Channel(v + s"_$i")))
          }
        )

      case IfThen(b, p) if evalB(b, subst) == true =>
        translateProcess(p, natRange, subst)
      case IfThen(_, _) => // if evalB(b, subst) == false
        P.Sum(List.empty)
