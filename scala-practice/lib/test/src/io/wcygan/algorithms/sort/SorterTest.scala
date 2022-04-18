package io.wcygan.algorithms.sort

import munit.Assertions._
import org.scalacheck.Prop._

class SorterTest extends munit.ScalaCheckSuite {
  val sorters: Array[Sorter] = Array(MergeSort, HeapSort, ParallelMergeSort, QuickSort)

  property("Lists should be sorted") {
    forAll { (list: IndexedSeq[Int]) =>
      sorters.foreach(sorter => {
        val want = list.sorted
        val got = sorter.sort(list)
        assert(want == got, s"Using sorter: ${sorter.getClass.toString}")
      })
    }
  }
}
