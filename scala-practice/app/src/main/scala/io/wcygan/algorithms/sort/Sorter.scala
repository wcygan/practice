package io.wcygan.algorithms.sort

trait Sorter {
  def sort[T: Ordering](arr: IndexedSeq[T]): IndexedSeq[T]
}
