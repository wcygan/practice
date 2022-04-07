package io.wcygan.algorithms.search

import scala.annotation.tailrec

object BinarySearch {
  @tailrec
  def search[T: Ordering](value: T, arr: IndexedSeq[T]): Option[T] = {
    if (arr.isEmpty) return None
    if (arr.length == 1 && !arr(0).equals(value)) return None
    val mid = arr(arr.length / 2)
    if (mid.equals(value)) Some(mid)
    else {
      val (lo, hi) = if (Ordering[T].gt(mid, value)) {
        (0, arr.length / 2)
      } else {
        (arr.length / 2, arr.length)
      }
      search(value, arr.slice(lo, hi))
    }
  }
}
