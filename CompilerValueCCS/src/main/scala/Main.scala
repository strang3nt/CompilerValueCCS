package main.scala

import main.scala.ast.Aexpr._
import main.scala.ast._
import main.scala.parser.ValueCCSParser
import main.scala.parser.ValueCCSParser.program
import main.scala.compiler._
import main.scala.compiler.ValueCCSCompilationError._
import swing._

class UI extends MainFrame:
  def restrictHeight(s: Component) =
    s.maximumSize = new Dimension(Short.MaxValue, s.preferredSize.height)

  title = "Compiler CCS Value Passing into CCS"
  
  def newField: TextField = new TextField {
    text = "0"
    columns = 5
    horizontalAlignment = Alignment.Right
  }

  val ccs_vp_program = newField
  val ccs_translate_err = new TextPane { visible = false; editable = false; }
  val lowerBound = newField
  val upperBound = newField
  val upper_bound_err = new TextPane { visible = false; editable = false; }

  val commentField = new TextArea { rows = 8; lineWrap = true; wordWrap = true; editable = false; }

  restrictHeight(ccs_vp_program)

  contents = new BoxPanel(Orientation.Vertical) {
    contents += new BoxPanel(Orientation.Horizontal) {
      contents += new Label("CCS Value Passing Program:")
      contents += Swing.HStrut(5)
      contents += ccs_vp_program
      contents += Swing.HStrut(5)
      contents += ccs_translate_err
    }
    contents += new BoxPanel(Orientation.Horizontal) {
      contents += new Label("Number set lower bound:")
      contents += Swing.HStrut(5)
      contents += lowerBound
    }
    contents += new BoxPanel(Orientation.Horizontal) {
      contents += new Label("Number set upper bound:")
      contents += Swing.HStrut(5)
      contents += upperBound
      contents += Swing.HStrut(5)
      contents += upper_bound_err
    }
    contents += Swing.VStrut(5)
    contents += new Label("Result")
    contents += Swing.VStrut(3)
    contents += new ScrollPane(commentField)
    contents += Swing.VStrut(5)
    contents += new BoxPanel(Orientation.Horizontal) {
      contents += Button("Compile") { compileValueCCS() }
    }
    for (e <- contents)
      e.xLayoutAlignment = 0.0
    border = Swing.EmptyBorder(10, 10, 10, 10)
  }

  def compileValueCCS() =
    println("Get value passing CCS program")
    val v = ccs_vp_program.text
    val proc = ValueCCSCompiler(v)
    println("Get size of number set")
    val u = Integer.parseUnsignedInt(upperBound.text)
    val l = Integer.parseUnsignedInt(lowerBound.text)
    println("Checking if bounds are correct")
    
    if l > l then
        upper_bound_err.text = "Upper bound should be >= than lower bound"
        upper_bound_err.foreground = java.awt.Color.RED
        upper_bound_err.visible = true
    else
      proc match
        case Right(valueCCS) =>
          val result = PureCCSCompiler(valueCCS, l, u).mkString("\n")
          println(result)
          commentField.text = result
        case Left(ValueCCSParserError(Location(_, y), msg) ) =>
          ccs_translate_err.text = s"Parsing error at column ${y} with message: ${msg}"
          ccs_translate_err.foreground = java.awt.Color.RED
          ccs_translate_err.visible = true
        case Left(ValueCCSLexerError(Location(_, y), msg)) =>
          ccs_translate_err.text = s"Lexer error at column ${y} with message: ${msg}"
          ccs_translate_err.foreground = java.awt.Color.RED
          ccs_translate_err.visible = true

object Main extends App:

  val ui = new UI
  ui.visible = true
