package io.wcygan.algorithms.sorting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class QuickSortTest {
  @ParameterizedTest
  @MethodSource("integerArrayDataProvider")
  public void testIntegerSort(Integer[] given, Integer[] expected) {
    Sorter<Integer> quickSort = new QuickSort<>();
    quickSort.sort(given);
    assertArrayEquals(expected, given);
  }

  private static Stream<Arguments> integerArrayDataProvider() {
    return Stream.of(
        Arguments.of(new Integer[] {2, 3, 1}, new Integer[] {1, 2, 3}),
        Arguments.of(new Integer[] {2, 3, 2, 3}, new Integer[] {2, 2, 3, 3}),
        Arguments.of(new Integer[] {1, 4, 2, 3}, new Integer[] {1, 2, 3, 4}),
        Arguments.of(new Integer[] {77, 3, 1, 2, 77, 34}, new Integer[] {1, 2, 3, 34, 77, 77}),
        Arguments.of(new Integer[] {9, 9, 9, 2, 9, 9, 9}, new Integer[] {2, 9, 9, 9, 9, 9, 9}),
        Arguments.of(
            new Integer[] {1, 2, 1, 2, 1, 2, 1, 2}, new Integer[] {1, 1, 1, 1, 2, 2, 2, 2}),
        Arguments.of(new Integer[] {0, 1, -1}, new Integer[] {-1, 0, 1}),
        Arguments.of(
            new Integer[] {1000, 999, -999, -1000}, new Integer[] {-1000, -999, 999, 1000}));
  }
}
