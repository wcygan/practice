package io.wcygan.algorithms.search

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BinarySearchTest extends AnyFunSuite {
  test("Search for 3") {
    val arr = Array(1, 2, 3, 4, 5)
    val want = 3
    assert(BinarySearch.search(want, arr).contains(want))
  }

  test("Search for 5") {
    val arr = Array(1, 2, 3, 4, 5)
    val want = 5
    assert(BinarySearch.search(want, arr).contains(want))
  }

  test("Search for 10") {
    val arr = Array(1, 2, 3, 4, 5)
    val want = 10
    assert(BinarySearch.search(want, arr).isEmpty)
  }

  test("Search for 56783") {
    val arr = Range.inclusive(0, 100000)
    val want = 56783
    assert(BinarySearch.search(want, arr).contains(want))
  }
}
