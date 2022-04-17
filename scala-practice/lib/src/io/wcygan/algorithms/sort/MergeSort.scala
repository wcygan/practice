package io.wcygan.algorithms.sort

object MergeSort extends Sorter {
  override def sort[T: Ordering](items: IndexedSeq[T]): IndexedSeq[T] = {
    if (items.length <= 1) items
    else {
      val (left, right) = items.splitAt(items.length / 2)
      merge(sort(left), sort(right))
    }
  }

  def merge[T: Ordering](
      sortedLeft: IndexedSeq[T],
      sortedRight: IndexedSeq[T]
  ): IndexedSeq[T] = {
    var leftIdx = 0
    var rightIdx = 0
    val output = IndexedSeq.newBuilder[T]
    while (leftIdx < sortedLeft.length || rightIdx < sortedRight.length) {
      val takeLeft =
        (leftIdx < sortedLeft.length, rightIdx < sortedRight.length) match {
          case (true, false) => true
          case (false, true) => false
          case (true, true) =>
            Ordering[T].lt(sortedLeft(leftIdx), sortedRight(rightIdx))
        }
      if (takeLeft) {
        output += sortedLeft(leftIdx)
        leftIdx += 1
      } else {
        output += sortedRight(rightIdx)
        rightIdx += 1
      }
    }
    output.result()
  }
}
