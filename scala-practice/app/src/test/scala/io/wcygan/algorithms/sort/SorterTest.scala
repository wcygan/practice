package io.wcygan.algorithms.sort

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SorterTest extends AnyFunSuite {

  val sorters: Array[Sorter] = Array(MergeSort, HeapSort)

  test("MergeSort Int example 0") {
    sorters.foreach(sorter => {
      val arr = Array(0)
      assert(sorter.sort(arr).sameElements(arr.sorted))
    })
  }

  test("MergeSort Int example 1") {
    sorters.foreach(sorter => {
      val arr = Array(3, 1, 2)
      assert(sorter.sort(arr).sameElements(arr.sorted))
    })
  }

  test("MergeSort Int example 2") {
    sorters.foreach(sorter => {
      val arr = Array(5, 5, 2, 5, 1, 3)
      assert(sorter.sort(arr).sameElements(arr.sorted))
    })
  }

  test("MergeSort Int example 3") {
    sorters.foreach(sorter => {
      val arr = Array(-1, -99, 10, 0, 1)
      assert(sorter.sort(arr).sameElements(arr.sorted))
    })
  }

  test("MergeSort String example 1") {
    sorters.foreach(sorter => {
      val arr = Array("A", "C", "D", "B")
      assert(sorter.sort(arr).sameElements(arr.sorted))
    })
  }

  test("MergeSort String example 2") {
    sorters.foreach(sorter => {
      val arr = Array("Hello", "World", "I", "Am", "A", "Sort!")
      assert(sorter.sort(arr).sameElements(arr.sorted))
    })
  }
}