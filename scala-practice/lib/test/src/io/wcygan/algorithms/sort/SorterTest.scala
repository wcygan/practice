package io.wcygan.algorithms.sort

import utest._

object SorterTest extends TestSuite {
  def tests: Tests = Tests {

    val sorters: Array[Sorter] = Array(MergeSort, HeapSort)

    test("MergeSort Int example 0") {
      sorters.foreach(sorter => {
        val arr = Array(0)
        val want = arr.sorted.toSeq
        val got = sorter.sort(arr.toIndexedSeq)
        want ==> got
      })
    }

    test("MergeSort Int example 1") {
      sorters.foreach(sorter => {
        val arr = Array(3, 1, 2)
        val want = arr.sorted.toSeq
        val got = sorter.sort(arr.toIndexedSeq)
        want ==> got
      })
    }

    test("MergeSort Int example 2") {
      sorters.foreach(sorter => {
        val arr = Array(5, 5, 2, 5, 1, 3)
        val want = arr.sorted.toSeq
        val got = sorter.sort(arr.toIndexedSeq)
        want ==> got
      })
    }

    test("MergeSort Int example 3") {
      sorters.foreach(sorter => {
        val arr = Array(-1, -99, 10, 0, 1)
        val want = arr.sorted.toSeq
        val got = sorter.sort(arr.toIndexedSeq)
        want ==> got
      })
    }

    test("MergeSort String example 1") {
      sorters.foreach(sorter => {
        val arr = Array("A", "C", "D", "B")
        val want = arr.sorted.toSeq
        val got = sorter.sort(arr.toIndexedSeq)
        want ==> got
      })
    }

    test("MergeSort String example 2") {
      sorters.foreach(sorter => {
        val arr = Array("Hello", "World", "I", "Am", "A", "Sort!")
        val want = arr.sorted.toSeq
        val got = sorter.sort(arr.toIndexedSeq)
        want ==> got
      })
    }
  }
}