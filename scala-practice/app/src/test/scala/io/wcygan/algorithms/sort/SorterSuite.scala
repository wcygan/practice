package io.wcygan.algorithms.sort

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SorterSuite extends AnyFunSuite {

  val sorters: Array[Sorter] = Array(MergeSort)

  test("MergeSort example 0") {
    sorters.foreach(sorter => {
      val arr = Array(0)
      val got = sorter.sort(arr)
      val want = Array(0)
      assert(got.sameElements(want))
    })
  }

  test("MergeSort example 1") {
    sorters.foreach(sorter => {
      val arr = Array(3, 1, 2)
      val got = sorter.sort(arr)
      val want = Array(1, 2, 3)
      assert(got.sameElements(want))
    })
  }

  test("MergeSort example 2") {
    sorters.foreach(sorter => {
      val arr = Array(5, 5, 2, 5, 1, 3)
      val got = sorter.sort(arr)
      val want = Array(1, 2, 3, 5, 5, 5)
      assert(got.sameElements(want))
    })
  }

  test("MergeSort example 3") {
    sorters.foreach(sorter => {
      val arr = Array(-1, -99, 10, 0, 1)
      val got = sorter.sort(arr)
      val want = Array(-99, -1, 0, 1, 10)
      assert(got.sameElements(want))
    })
  }
}