// build.sc
import mill._, scalalib._

object lib extends ScalaModule {
  def scalaVersion = "3.1.1"
  object test extends Tests {
    override def ivyDeps = Agg(ivy"com.lihaoyi::utest:0.7.11", ivy"com.lihaoyi::os-lib:0.8.0")
    override def testFramework = "utest.runner.Framework"
  }
}
