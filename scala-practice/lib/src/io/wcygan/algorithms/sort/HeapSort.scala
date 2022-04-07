package io.wcygan.algorithms.sort

import scala.collection.mutable

object HeapSort extends Sorter {
  override def sort[T: Ordering](arr: IndexedSeq[T]): IndexedSeq[T] = {
    mutable.PriorityQueue.empty[T](
      implicitly[Ordering[T]].reverse
    ).addAll(arr)
      .dequeueAll
      .toIndexedSeq
  }
}
