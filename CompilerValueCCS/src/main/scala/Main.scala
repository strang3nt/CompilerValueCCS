package main.scala

import scalafx.Includes._
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.text.Text
import scalafx.scene.control._
import scalafx.scene.layout.{GridPane, VBox}
import scalafx.beans.property.BooleanProperty
import scalafx.beans.property.IntegerProperty
import scalafx.scene.layout.ColumnConstraints
import scalafx.scene.layout.Priority

import main.scala.compiler._
import main.scala.compiler.ValueCCSCompilationError._

def compileValueCCS(
    ccs_vp: String,
    lb: Int,
    ub: Int
): Either[ValueCCSCompilationError, String] =
  ValueCCSCompiler(ccs_vp) match
    case Right(r) => Right(PureCCSCompiler(r, lb, ub).mkString("\n"))
    case Left(r)  => Left(r)

object Main extends JFXApp3:
  override def start(): Unit =
    stage = new PrimaryStage:
      scene = new Scene(400, 300):
        title = "CCS Value Passing compiler to CCS"
        resizable = true
        val report = new TextArea:
          text = "CCS VP translated"
          wrapText = true
          disable = true

        val valueCCSString = new TextField:
          promptText = "CCS Value Passing string"

        val isLBint = BooleanProperty(false)
        val isUBint = BooleanProperty(false)
        val ubValue = IntegerProperty(-1)
        val lbValue = IntegerProperty(-1)

        val lowerBound = new TextField { promptText = "Lower Bound" }
        lowerBound.text.onChange { (_, _, n) =>
          isLBint.update(n.toIntOption match
            case Some(x) => lbValue.update(x); x >= 0
            case None    => false
          )
        }

        val upperBound = new TextField { promptText = "Upper Bound" }
        upperBound.text.onChange { (_, _, n) =>
          isUBint.update(n.toIntOption match
            case Some(x) => ubValue.update(x); x >= 0
            case None    => false
          )
        }

        val compileButton = new Button("Compile"):
          disable <== !(isUBint && isLBint && (lbValue <= ubValue))
          onAction = _ =>
            compileValueCCS(
              valueCCSString.text.value,
              lowerBound.text.value.toInt,
              upperBound.text.value.toInt
            ) match
              case Right(r) => report.text = r
              case Left(e)  => report.text = e.toString

        val grid = new GridPane:
          hgap = 10
          vgap = 10
          padding = Insets(20, 20, 10, 10)
          columnConstraints = List(
            new ColumnConstraints() { hgrow = Priority.Never },
            new ColumnConstraints() { hgrow = Priority.Always }
          )
          add(new Label("CCS Value Passing program:"), 0, 0)
          add(valueCCSString, 1, 0)
          add(new Label("Lower Bound:"), 0, 1)
          add(lowerBound, 1, 1)
          add(new Label("Upper Bound:"), 0, 2)
          add(upperBound, 1, 2)
          add(compileButton, 1, 3)

        root = new VBox:
          fillWidth = true
          padding = Insets(top = 24, right = 64, bottom = 24, left = 64)
          children = List( grid, report)
