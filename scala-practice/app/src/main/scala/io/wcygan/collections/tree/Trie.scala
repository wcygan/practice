package io.wcygan.collections.tree

class Trie {
  class Node(var hasValue: Boolean,
             var children: collection.mutable.Map[Char, Node] = collection.mutable.Map())
  val root = new Node(false)

  def add(s: String): Unit = {
    var curr = root
    for (c <- s) curr = curr.children.getOrElseUpdate(c, new Node(false))
    curr.hasValue = true
  }

  def contains(s: String): Boolean = {
    var curr = Option(root)
    for (c <- s if curr.nonEmpty) curr = curr.get.children.get(c)
    curr.exists(_.hasValue)
  }
}
