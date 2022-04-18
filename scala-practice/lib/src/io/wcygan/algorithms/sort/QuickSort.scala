package io.wcygan.algorithms.sort

object QuickSort extends Sorter {
  override def sort[T: Ordering](items: IndexedSeq[T]): IndexedSeq[T] = {
    if (items.length <= 1) items
    else {
      val pivot = items(items.length / 2)
      IndexedSeq.concat(
        sort(items.filter(Ordering[T].lt(_, pivot))),
        items.filter(Ordering[T].equiv(_, pivot)),
        sort(items.filter(Ordering[T].gt(_, pivot)))
      )
    }
  }
}
