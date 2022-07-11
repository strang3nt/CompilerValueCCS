import mill._, scalalib._, scalafmt._

object CompilerValueCCS extends ScalaModule with ScalafmtModule {
  def scalaVersion = "3.1.3"

  def resolutionCustomizer = T.task {
    Some( (r: coursier.core.Resolution) =>
      r.withOsInfo(coursier.core.Activation.Os.fromProperties(sys.props.toMap))
    )
  }

  // Add dependency on JavaFX libraries
  val javaFXVersion  = "18.0.1"
  val scalaFXVersion = "18.0.1-R27"
  val javaFXModules  = List("base", "controls", "fxml", "graphics", "media", "swing", "web")
    .map(m => ivy"org.openjfx:javafx-$m:$javaFXVersion")

  
  def ivyDeps = Agg(
      ivy"org.scalafx::scalafx:$scalaFXVersion",
      ivy"org.scala-lang.modules::scala-parser-combinators:2.1.1"

  ) ++ javaFXModules

  object test extends Tests {
    def ivyDeps = Agg(ivy"org.scalameta::munit:0.7.29")
    def testFramework = "munit.Framework"
  } 
}
