package io.wcygan.collections.tree

/** A trie acts as an efficient set for strings
  */
class Trie {
  val root = new Node(false)

  /** Add a string to the Trie
    * @param s the string to add
    */
  def add(s: String): Unit = {
    var curr = root
    for (c <- s) curr = curr.children.getOrElseUpdate(c, new Node(false))
    curr.hasValue = true
  }

  /** Check if a string is in the Trie
    * @param s the string to check
    * @return true if the string is in the Trie, else false
    */
  def contains(s: String): Boolean = {
    var curr = Option(root)
    for (c <- s if curr.nonEmpty) curr = curr.get.children.get(c)
    curr.exists(_.hasValue)
  }

  /** Finds all strings in the Trie which are a prefix of s
    * @param s the string to match
    * @return all strings in the Trie which are a prefix of s
    */
  def prefixMatchingStrings(s: String): Set[String] = {
    prefixMatchingIndices(s).map(s.substring(0, _))
  }

  /** Finds all ending indices of strings which are a prefix of s
    * @param s the string to match
    * @return all ending indices of strings which are a prefix of s
    */
  def prefixMatchingIndices(s: String): Set[Int] = {
    var curr = Option(root)
    val out = Set.newBuilder[Int]

    for ((c, i) <- s.zipWithIndex if curr.nonEmpty) {
      if (curr.get.hasValue) out += i
      curr = curr.get.children.get(c)
    }

    if (curr.exists(_.hasValue)) out += s.length
    out.result()
  }

  /** Finds all strings in the Trie which match the prefix s
    * @param s the prefix to match strings with
    * @return all strings in the Trie which match the prefix s
    */
  def stringsMatchingPrefix(s: String): Set[String] = {
    var curr = Option(root)
    for (c <- s if curr.nonEmpty) curr = curr.get.children.get(c)
    if (curr.isEmpty) Set()
    else {
      val out = Set.newBuilder[String]

      def recurse(curr: Node, path: List[Char]): Unit = {
        if (curr.hasValue) out += (s + path.reverse.mkString)
        for ((c, n) <- curr.children) recurse(n, c :: path)
      }

      recurse(curr.get, Nil)
      out.result()
    }
  }

  class Node(
      var hasValue: Boolean,
      var children: collection.mutable.Map[Char, Node] =
        collection.mutable.Map()
  )
}
