import mill._, scalalib._

object CompilerValueCCS extends ScalaModule {
  def scalaVersion = "3.1.1"
  def ivyDeps = Agg(
      ivy"org.typelevel::cats-parse:0.3.6"
  )

  object test extends Tests {
    def ivyDeps = Agg(ivy"org.scalameta::munit:0.7.29")
    def testFrameworks = Seq("munit.Framework")
  }
}