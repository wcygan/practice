// build.sc

import mill._
import mill.scalalib._

object lib extends ScalaModule {
  def scalaVersion = "3.1.1"

  def munitVersion = T {
    "0.7.29"
  }

  def catsVersion = T {
    "2.7.0"
  }

  override def ivyDeps = Agg(
    ivy"org.typelevel::cats-core::2.7.0",
    ivy"org.asynchttpclient:async-http-client:2.5.2",
    ivy"com.atlassian.commonmark:commonmark:0.13.1",
    ivy"com.lihaoyi::castor:0.2.1",
    ivy"com.lihaoyi::scalatags:0.11.1",
    ivy"com.lihaoyi::cask:0.8.0",
    ivy"com.lihaoyi::requests:0.7.0"
  )

  object test extends Tests with TestModule.Munit {
    override def ivyDeps =
      Agg(
        ivy"org.scalameta::munit::0.7.29",
        ivy"io.chrisdavenport::cats-scalacheck:0.3.1",
        ivy"org.scalameta::munit-scalacheck::${munitVersion()}",
        ivy"org.scalacheck::scalacheck:1.15.4"
      )
  }
}
