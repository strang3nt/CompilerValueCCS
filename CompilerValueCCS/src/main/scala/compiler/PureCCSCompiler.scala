package main.scala.compiler

import main.scala.ast.Aexpr._
import main.scala.ast.Bexpr._
import main.scala.ast.CommonAst._
import main.scala.ast.Natural
import main.scala.ast.PureCCS as P
import main.scala.ast.ValueCCS as V
import main.scala.ast.ValueCCS.Constant
import main.scala.eval.EvalAexpr.eval as evalA
import main.scala.eval.EvalBexpr.eval as evalB
import main.scala.parser.ValueCCSParser
import main.scala.process.{
  PureCCSProcess,
  ValueCCSProcess,
  Constant as ProcessConstant
}

import scala.collection.immutable.Map

object PureCCSCompiler {

  def apply(
      program: ValueCCSProcess,
      lowerBound: Int,
      upperInclBound: Int
  ): List[PureCCSProcess] =
    val ValueCCSProcess(name, process) = program
    val natRange = (lowerBound to upperInclBound).toSet
    name match
      case ProcessConstant(n, Some(l)) =>
        var substCombinations: List[List[Natural]] =
          (List
            .fill(l.length)(natRange))
            .flatten
            .combinations(l.length)
            .toList
            .foldLeft(List.empty)((acc, l) =>
              acc ++ l.map(Natural(_)).permutations.toList
            )
        substCombinations.map((nats) =>
          PureCCSProcess(
            n + "_" + nats.mkString("_"),
            translateProcess(process, natRange, (l zip nats).toMap)
          )
        )
      case ProcessConstant(n, None) =>
        PureCCSProcess(n, translateProcess(process, natRange, Map.empty)) :: Nil

  private def translateProcess(
      src: V,
      natRange: Set[Int],
      subst: Map[Variable, Natural]
  ): P =
    src match
      case V.Constant(n, None) => P.Constant(n)

      case V.Constant(n, Some(es)) =>
        P.Constant(
          n + "_"
            + es.map(e => evalA(e, subst)).mkString("_")
        )

      case V.InputCh(Channel(n), Some(v), p) =>
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

      case V.InputCh(Channel(n), None, p) =>
        P.InputCh(Channel(n), translateProcess(p, natRange, subst))

      case V.InputCh(Tau(), _, p) =>
        P.InputCh(Tau(), translateProcess(p, natRange, subst))

      case V.OutputCh(Channel(n), Some(e), p) =>
        val r = evalA(e, subst)
        P.OutputCh(Channel(n + s"_$r"), translateProcess(p, natRange, subst))

      // TODO: check if field can be empty
      case V.OutputCh(Channel(n), None, p) =>
        P.OutputCh(Channel(n), translateProcess(p, natRange, subst))

      case V.Sum(l) if l.isEmpty =>
        P.Sum(List.empty)

      case V.Sum(l) =>
        P.Sum(l.map(p => translateProcess(p, natRange, subst)))

      case V.Par(left, right) =>
        P.Par(
          translateProcess(left, natRange, subst),
          translateProcess(right, natRange, subst)
        )

      case V.Restrict(p, l) =>
        P.Restrict(
          translateProcess(p, natRange, subst),
          l.foldLeft(List.empty) { case (acc, Channel(n)) =>
            acc ++ natRange.map(i => Channel(n + s"_$i"))
          }
        )

      case V.Redirection(p, cs) =>
        P.Redirection(
          translateProcess(p, natRange, subst),
          cs.foldLeft(List.empty) { case (acc, (Channel(n), Channel(v))) =>
            acc ++ natRange.map(i => (Channel(n + s"_$i"), Channel(v + s"_$i")))
          }
        )

      case V.IfThen(b, p) if evalB(b, subst) == true =>
        translateProcess(p, natRange, subst)
      case V.IfThen(_, _) => // if evalB(b, subst) == false
        P.Sum(List.empty)

}
