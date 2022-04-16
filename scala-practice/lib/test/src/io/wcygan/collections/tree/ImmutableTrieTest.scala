package io.wcygan.collections.tree

import munit.Assertions._

class ImmutableTrieTest extends munit.FunSuite {
  test("Immutable Trie example 1") {
    val t = new ImmutableTrie(List("Hello!"))
    assert(t.contains("Hello!"))
    assert(!t.contains("World!"))
  }

  test("Immutable Trie example 2") {
    val t = new ImmutableTrie(List("Hello", "Hello!", "He"))
    assert(t.contains("Hello!"))
    assert(t.contains("Hello"))
    assert(t.contains("He"))
  }

  test("Immutable Trie prefix indices example 1") {
    val t = new ImmutableTrie(List("Hi", "Hill"))
    val indices = t.prefixMatchingIndices("Hillbilly")
    assert(indices.contains(2))
    assert(indices.contains(4))
    assert(!indices.contains(1))
    assert(!indices.contains(3))
    assert(!indices.contains(5))
  }

  test("Immutable Trie prefix strings example 1") {
    val t = new ImmutableTrie(List("Hi", "Hill"))
    val strings = t.prefixMatchingStrings("Hillbilly")
    assert(strings.contains("Hi"))
    assert(strings.contains("Hill"))
    assert(!strings.contains("Hillbilly"))
  }

  test("Immutable Trie strings matching prefix example 1") {
    val t = new ImmutableTrie(List("Hi", "Hill"))
    val strings = t.stringsMatchingPrefix("H")
    assert(strings.contains("Hi"))
    assert(strings.contains("Hill"))
    assert(!strings.contains("H"))
    assert(!strings.contains("Hillbilly"))
  }

  test("Immutable Trie strings matching prefix example 2") {
    val t = new ImmutableTrie(
      List("Hello World!", "Hello!", "Hello Wally!", "Hello Will!")
    )
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
