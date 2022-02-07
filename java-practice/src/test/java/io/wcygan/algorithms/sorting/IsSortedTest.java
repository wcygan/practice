package io.wcygan.algorithms.sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IsSortedTest {
    @Test
    public void testAscending() {
        List<Integer> lst = List.of(1, 2, 3);
        Assertions.assertTrue(SortingAlgorithm.isSorted(lst, true));
        Assertions.assertFalse(SortingAlgorithm.isSorted(lst, false));
    }

    @Test
    public void testDescending() {
        List<Integer> lst = List.of(3, 2, 1);
        Assertions.assertTrue(SortingAlgorithm.isSorted(lst, false));
        Assertions.assertFalse(SortingAlgorithm.isSorted(lst, true));
    }
}
