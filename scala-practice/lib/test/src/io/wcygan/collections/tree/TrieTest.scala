package io.wcygan.collections.tree

import munit.Assertions._

class TrieTest extends munit.FunSuite {
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

  test("Trie prefix indices example 1") {
    val t = new Trie()
    t.add("Hi")
    t.add("Hill")
    val indices = t.prefixMatchingIndices("Hillbilly")
    assert(indices.contains(2))
    assert(indices.contains(4))
    assert(!indices.contains(1))
    assert(!indices.contains(3))
    assert(!indices.contains(5))
  }

  test("Trie prefix strings example 1") {
    val t = new Trie()
    t.add("Hi")
    t.add("Hill")
    val strings = t.prefixMatchingStrings("Hillbilly")
    assert(strings.contains("Hi"))
    assert(strings.contains("Hill"))
    assert(!strings.contains("Hillbilly"))
  }

  test("Trie strings matching prefix example 1") {
    val t = new Trie()
    t.add("Hi")
    t.add("Hill")
    val strings = t.stringsMatchingPrefix("H")
    assert(strings.contains("Hi"))
    assert(strings.contains("Hill"))
    assert(!strings.contains("H"))
    assert(!strings.contains("Hillbilly"))
  }

  test("Trie strings matching prefix example 2") {
    val t = new Trie()
    t.add("Hello World!")
    t.add("Hello Will!")
    t.add("Hello!")
    t.add("Hello Wally!")
    val s1 = t.stringsMatchingPrefix("H")
    assert(s1.contains("Hello!"))
    assert(s1.contains("Hello World!"))
    assert(s1.contains("Hello Will!"))
    assert(s1.contains("Hello Wally!"))

    val s2 = t.stringsMatchingPrefix("Hello W")
    assert(!s2.contains("Hello!"))
    assert(s2.contains("Hello World!"))
    assert(s2.contains("Hello Will!"))
    assert(s2.contains("Hello Wally!"))
  }
}
