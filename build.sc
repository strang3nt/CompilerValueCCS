

object CompilerValueCCS extends ScalaModule with ScalafmtModule {
  def scalaVersion = "3.1.1"
  def ivyDeps = Agg(
      ivy"org.scala-lang.modules::scala-parser-combinators:2.1.1"
  )

  object test extends Tests {
    def ivyDeps = Agg(ivy"org.scalameta::munit:0.7.29")
    def testFramework = "munit.Framework"
  }
}