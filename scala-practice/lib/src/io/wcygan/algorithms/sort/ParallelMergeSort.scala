package io.wcygan.algorithms.sort

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration.Duration.Inf

object ParallelMergeSort extends Sorter {
  override def sort[T: Ordering](items: IndexedSeq[T]): IndexedSeq[T] = {
    Await.result(parallel(items), Inf)
  }

  def parallel[T: Ordering](arr: IndexedSeq[T]): Future[IndexedSeq[T]] = {
    if (arr.length <= 16) Future.successful(MergeSort.sort(arr))
    else {
      val (left, right) = arr.splitAt(arr.length / 2)
      parallel(left).zip(parallel(right)).map {
        case (sortedLeft, sortedRight) =>
          MergeSort.merge(sortedLeft, sortedRight)
      }
    }
  }
}
