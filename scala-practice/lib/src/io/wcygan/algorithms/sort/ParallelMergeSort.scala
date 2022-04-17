package io.wcygan.algorithms.sort
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration.Duration.Inf

object ParallelMergeSort extends Sorter {
  override def sort[T: Ordering](arr: IndexedSeq[T]): IndexedSeq[T] = {
    Await.result(parallel(arr), Inf)
  }

  def parallel[T: Ordering](arr: IndexedSeq[T]): Future[IndexedSeq[T]] = {
    if (arr.length <= 16) Future.successful(MergeSort.sort(arr))
    else {
      val (left, right) = arr.splitAt(arr.length / 2)
      parallel(left).zip(parallel(right)).map {
        case (sortedLeft, sortedRight) => merge(sortedLeft, sortedRight)
      }
    }
  }

  // TODO @wcygan: move this into MergeSort
  def merge[T: Ordering](
      sortedLeft: IndexedSeq[T],
      sortedRight: IndexedSeq[T]
  ) = {
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
