package io.wcygan.algorithms.sorting;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestBogoSort {
    @Test
    public void itMightWork() {
        List<Integer> lst = new ArrayList<>(List.of(1, 3, 2, 5, 4));
        new BogoSort<Integer>().sort(lst);
        assertThat(SortingAlgorithm.isSorted(lst, true)).isTrue();
    }

    @Test
    public void itMightWorkAgain() {
        List<Integer> lst = new ArrayList<>(List.of(1, 3, 8, 10, 10, 20, 15));
        new BogoSort<Integer>().sort(lst);
        assertThat(SortingAlgorithm.isSorted(lst, true)).isTrue();
    }
}
