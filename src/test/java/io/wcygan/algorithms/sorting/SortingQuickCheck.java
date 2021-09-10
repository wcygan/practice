package io.wcygan.algorithms.sorting;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.Size;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(JUnitQuickcheck.class)
public class SortingQuickCheck {

  @Property(trials = 50)
  public void testSortingAlgorithms(@Size(max = 5) List<Integer> actual) {
    List<Integer> expected = new ArrayList<>(actual);
    Collections.sort(expected);
    verifySort(new QuickSort<>(), expected, actual);
    verifySort(new SequentialMergeSort<>(), expected, actual);
    verifySort(new ParallelMergeSort<>(), expected, actual);
  }

  private void verifySort(Sorter<Integer> sorter, List<Integer> expected, List<Integer> actual) {
    Collections.shuffle(actual);
    sorter.sort(actual);
    Assertions.assertEquals(expected, actual);
  }
}
