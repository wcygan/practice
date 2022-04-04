package io.wcygan.collections.tree

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TrieSuite extends AnyFunSuite {

  test("Trie example 1") {
    val t = new Trie()
    t.add("Hello!")
    assert(t.contains("Hello!"))
    assert(!t.contains("World!"))
  }

  test("Trie example 2") {
    val t = new Trie()
    assert(!t.contains("Hello!"))
    t.add("Hello!")
    assert(!t.contains("Hello"))
    assert(!t.contains("He"))
    t.add("Hello")
    assert(t.contains("Hello"))
    t.add("He")
    assert(t.contains("He"))
  }
}