package io.wcygan.algorithms.graph

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ShortestPathTest extends AnyFunSuite {
  test("Shortest Path a -> d") {
    assert(
      ShortestPath.shortestPath(start = "a", dest = "d",
        graph = Map(
          "a" -> Seq("b", "c"),
          "b" -> Seq("c", "d"),
          "c" -> Seq("d"),
          "d" -> Seq()
        )) == List("a", "b", "d")
    )
  }

  test("Shortest Path a -> b") {
    assert(
      ShortestPath.shortestPath(start = "a", dest = "b",
        graph = Map(
          "a" -> Seq("b", "c"),
          "b" -> Seq("c", "d"),
          "c" -> Seq("d"),
          "d" -> Seq()
        )) == List("a", "b")
    )
  }

  test("Shortest Path a -> c") {
    assert(
      ShortestPath.shortestPath(start = "a", dest = "c",
        graph = Map(
          "a" -> Seq("b", "c"),
          "b" -> Seq("c", "d"),
          "c" -> Seq("d"),
          "d" -> Seq()
        )) == List("a", "c")
    )
  }

  test("Shortest Path d -> a") {
    assert(
      ShortestPath.shortestPath(start = "d", dest = "a",
        graph = Map(
          "a" -> Seq("b", "c"),
          "b" -> Seq("c", "d"),
          "c" -> Seq("d"),
          "d" -> Seq()
        )) == List()
    )
  }
}
