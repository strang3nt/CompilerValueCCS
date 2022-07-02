package main.scala.compiler

import scala.collection.immutable.Map

import main.scala.ast.CommonAst._
import main.scala.ast.Naturals._
import main.scala.ast.Aexpr._
import main.scala.ast.Bexpr._
import main.scala.ast.PureCCSAst.{PureCCS => P}
import main.scala.ast.ValueCCSAst.{ValueCCS => V}

import main.scala.eval.EvalAexpr.{eval => evalA}
import main.scala.eval.EvalBexpr.{eval => evalB}

import main.scala.parser.ValueCCSParser

import main.scala.process.CCSSystem._
import main.scala.process.{ValueCCSProcess, Constant => Proc}
import main.scala.process.PureCCSProcess

object ValueCCSCompiler {

  def compile(system: ValueCCSSystem, maxNat: Int): String =
    system.l
      .map { case ValueCCSProcess(name, process) =>
        name match
          case Proc(n, Some(l)) =>
            var substCombinations: List[List[Natural]] =
              (List
                .fill(l.length)(0 to maxNat))
                .flatten
                .combinations(l.length)
                .toList
                .foldLeft(List.empty)((acc, l) =>
                  acc ++ l.map(Natural(_)).permutations.toList
                )
            substCombinations.map((nats) =>
              PureCCSProcess(
                n + "_" + nats.mkString("_"),
                translateProcess(process, maxNat, (l zip nats).toMap)
              )
            )
          case Proc(n, None) =>
            PureCCSProcess(n, translateProcess(process, maxNat, Map.empty))
      }
      .mkString("\n")

  private def translateProcess(
      src: V,
      maxNat: Int,
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
          (0 to maxNat)
            .map(i =>
              P.InputCh(
                Channel(n + s"_$i"),
                translateProcess(p, maxNat, subst + (v -> Natural(i)))
              )
            )
            .toList
        )

      case V.InputCh(Channel(n), None, p) =>
        P.InputCh(Channel(n), translateProcess(p, maxNat, subst))

      case V.InputCh(Tau(), _, p) =>
        P.InputCh(Tau(), translateProcess(p, maxNat, subst))

      case V.OutputCh(Channel(n), Some(e), p) =>
        val r = evalA(e, subst)
        P.OutputCh(Channel(n + s"_$r"), translateProcess(p, maxNat, subst))

      // TODO: check if field can be empty
      case V.OutputCh(Channel(n), None, p) =>
        P.OutputCh(Channel(n), translateProcess(p, maxNat, subst))

      case V.Sum(l) if l.isEmpty =>
        P.Sum(List.empty)

      case V.Sum(l) =>
        P.Sum(l.map(p => translateProcess(p, maxNat, subst)))

      case V.Par(left, right) =>
        P.Par(
          translateProcess(left, maxNat, subst),
          translateProcess(right, maxNat, subst)
        )

      case V.Restrict(p, l) =>
        P.Restrict(
          translateProcess(p, maxNat, subst),
          l.foldLeft(List.empty) { case (acc, Channel(n)) =>
            acc ++ (0 to maxNat).map(i => Channel(n + s"_$i"))
          }
        )

      case V.Redirection(p, cs) =>
        P.Redirection(
          translateProcess(p, maxNat, subst),
          cs.foldLeft(List.empty) { case (acc, (Channel(n), Channel(v))) =>
            acc ++ (0 to maxNat).map(i =>
              (Channel(n + s"_$i"), Channel(v + s"_$i"))
            )
          }
        )

      case V.IfThen(b, p) if evalB(b, subst) == true =>
        translateProcess(p, maxNat, subst)
      case V.IfThen(_, _) => // if evalB(b, subst) == false
        P.Sum(List.empty)

}
