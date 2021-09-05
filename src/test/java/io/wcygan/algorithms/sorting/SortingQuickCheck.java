package io.wcygan.algorithms.sorting;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(JUnitQuickcheck.class)
public class SortingQuickCheck {

  @Property(trials = 25)
  public void testSortingAlgorithms(List<Integer> actual) {
    List<Integer> expected = new ArrayList<>(actual);
    Collections.sort(expected);
    verifySort(new QuickSort<>(), expected, actual);
  }

  private void verifySort(Sorter<Integer> sorter, List<Integer> expected, List<Integer> actual) {
    Collections.shuffle(actual);
    sorter.sort(actual);
    Assertions.assertEquals(expected, actual);
  }
}
