package io.wcygan.algorithms.sort

trait Sorter {
  def sort[T: Ordering](items: IndexedSeq[T]): IndexedSeq[T]
}
