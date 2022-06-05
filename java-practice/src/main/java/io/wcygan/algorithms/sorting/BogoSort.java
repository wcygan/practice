package io.wcygan.algorithms.sorting;

import java.util.Collections;
import java.util.List;

// please do not use this lol
public class BogoSort<T extends Comparable<T>> implements SortingAlgorithm<T> {
    @Override
    public void sort(List<T> lst) {
        while (!SortingAlgorithm.isSorted(lst, true)) {
            Collections.shuffle(lst);
        }
    }
}
