package io.wcygan.algorithms.search

object BreadthFirstSearch {
  def search[T](start: T, graph: Map[T, Seq[T]]): Set[T] = {
    val seen = collection.mutable.Set(start)
    val queue = collection.mutable.ArrayDeque(start)

    while (queue.nonEmpty) {
      val curr = queue.removeHead()
      for (next <- graph(curr) if !seen.contains(next)) {
        seen.add(next)
        queue.append(next)
      }
    }

    seen.toSet
  }
}
