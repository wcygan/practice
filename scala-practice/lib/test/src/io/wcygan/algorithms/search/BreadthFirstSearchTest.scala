package io.wcygan.algorithms.search

import munit.Assertions._

class BreadthFirstSearchTest extends munit.FunSuite {
  test("BFS 1") {
    val start = "a"
    val graph = Map(
      "a" -> Seq("b", "c"),
      "b" -> Seq("c", "d"),
      "c" -> Seq("d"),
      "d" -> Seq()
    )
    val got = BreadthFirstSearch.search(start, graph)
    assert(got.contains("a"))
    assert(got.contains("b"))
    assert(got.contains("c"))
    assert(got.contains("d"))
  }

  test("BFS 2") {
    val start = "b"
    val graph = Map(
      "a" -> Seq("b", "c"),
      "b" -> Seq("c", "d"),
      "c" -> Seq("d"),
      "d" -> Seq()
    )
    val got = BreadthFirstSearch.search(start, graph)
    assert(!got.contains("a"))
    assert(got.contains("b"))
    assert(got.contains("c"))
    assert(got.contains("d"))
  }

  test("BFS 3") {
    val start = "c"
    val graph = Map(
      "a" -> Seq("b", "c"),
      "b" -> Seq("c", "d"),
      "c" -> Seq("d"),
      "d" -> Seq()
    )
    val got = BreadthFirstSearch.search(start, graph)
    assert(got.size == 2)
    assert(!got.contains("a"))
    assert(!got.contains("b"))
    assert(got.contains("c"))
    assert(got.contains("d"))
  }

  test("BFS 4") {
    val start = "d"
    val graph = Map(
      "a" -> Seq("b", "c"),
      "b" -> Seq("c", "d"),
      "c" -> Seq("d"),
      "d" -> Seq()
    )
    val got = BreadthFirstSearch.search(start, graph)
    assert(got.size == 1)
    assert(!got.contains("a"))
    assert(!got.contains("b"))
    assert(!got.contains("c"))
    assert(got.contains("d"))
  }

  test("Circular BFS 1") {
    val start = "a"
    val graph = Map(
      "a" -> Seq("b"),
      "b" -> Seq("c"),
      "c" -> Seq("d"),
      "d" -> Seq("a")
    )
    val got = BreadthFirstSearch.search(start, graph)
    assert(got.size == 4)
    assert(got.contains("a"))
    assert(got.contains("b"))
    assert(got.contains("c"))
    assert(got.contains("d"))
  }

  test("Circular BFS 2") {
    val start = "b"
    val graph = Map(
      "a" -> Seq("b"),
      "b" -> Seq("c"),
      "c" -> Seq("d"),
      "d" -> Seq("a")
    )
    val got = BreadthFirstSearch.search(start, graph)
    assert(got.size == 4)
    assert(got.contains("a"))
    assert(got.contains("b"))
    assert(got.contains("c"))
    assert(got.contains("d"))
  }

  test("Circular BFS 3") {
    val start = "c"
    val graph = Map(
      "a" -> Seq("b"),
      "b" -> Seq("c"),
      "c" -> Seq("d"),
      "d" -> Seq("a")
    )
    val got = BreadthFirstSearch.search(start, graph)
    assert(got.size == 4)
    assert(got.contains("a"))
    assert(got.contains("b"))
    assert(got.contains("c"))
    assert(got.contains("d"))
  }

  test("Circular BFS 4") {
    val start = "d"
    val graph = Map(
      "a" -> Seq("b"),
      "b" -> Seq("c"),
      "c" -> Seq("d"),
      "d" -> Seq("a")
    )
    val got = BreadthFirstSearch.search(start, graph)
    assert(got.size == 4)
    assert(got.contains("a"))
    assert(got.contains("b"))
    assert(got.contains("c"))
    assert(got.contains("d"))
  }
}
