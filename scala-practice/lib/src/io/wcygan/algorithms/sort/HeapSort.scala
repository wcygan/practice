package io.wcygan.algorithms.sort

import scala.collection.mutable

object HeapSort extends Sorter {
  override def sort[T: Ordering](items: IndexedSeq[T]): IndexedSeq[T] = {
    mutable.PriorityQueue
      .empty[T](
        implicitly[Ordering[T]].reverse
      )
      .addAll(items)
      .dequeueAll
      .toIndexedSeq
  }
}
