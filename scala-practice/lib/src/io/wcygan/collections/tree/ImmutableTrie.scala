package io.wcygan.collections.tree

/** A trie acts as an efficient set for strings
  */
class ImmutableTrie(strings: Seq[String]) {
  val trie = new Trie()
  strings.foreach(trie.add)

  /** Check if a string is in the Trie
    * @param s the string to check
    * @return true if the string is in the Trie, else false
    */
  def contains(s: String): Boolean = {
    trie.contains(s)
  }

  /** Finds all ending indices of strings which are a prefix of s
    * @param s the string to match
    * @return all ending indices of strings which are a prefix of s
    */
  def prefixMatchingIndices(s: String): Set[Int] = {
    trie.prefixMatchingIndices(s)
  }

  /** Finds all strings in the Trie which are a prefix of s
    * @param s the string to match
    * @return all strings in the Trie which are a prefix of s
    */
  def prefixMatchingStrings(s: String): Set[String] = {
    trie.prefixMatchingStrings(s)
  }

  /** Finds all strings in the Trie which match the prefix s
    * @param s the prefix to match strings with
    * @return all strings in the Trie which match the prefix s
    */
  def stringsMatchingPrefix(s: String): Set[String] = {
    trie.stringsMatchingPrefix(s)
  }
}
