import $ivy.`com.goyeau::mill-scalafix::0.2.10`
import com.goyeau.mill.scalafix.ScalafixModule
import mill._, scalalib._, scalafmt._ 

object CompilerValueCCS extends ScalaModule with ScalafmtModule with ScalafixModule {
  def scalaVersion = "3.1.3"

  def resolutionCustomizer = T.task {
    Some( (r: coursier.core.Resolution) =>
      r.withOsInfo(coursier.core.Activation.Os.fromProperties(sys.props.toMap))
    )
  }

  def scalacOptions = Seq(
      "-deprecation",
      "-feature"
  )

  def ivyDeps = Agg(
      ivy"org.scalafx::scalafx:18.0.2-R29",
      ivy"org.antlr:antlr4-runtime:4.10.1",
  )

  object test extends Tests {
    def ivyDeps = Agg(ivy"org.scalameta::munit:0.7.29")
    def testFramework = "munit.Framework"
  }
}
