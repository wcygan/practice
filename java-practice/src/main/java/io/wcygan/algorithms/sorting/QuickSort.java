package io.wcygan.algorithms.sorting;

import java.util.List;

/**
 * Implements QuickSort adapted from CLRS Chapter 7
 *
 * @param <T> the type of the values to sort
 */
public class QuickSort<T extends Comparable<T>> implements SortingAlgorithm<T> {

    @Override
    public void sort(List<T> lst) {
        quicksort(lst, 0, lst.size() - 1);
    }

    private void quicksort(List<T> lst, int lo, int hi) {
        if (lo < hi) {
            int mid = partition(lst, lo, hi);
            quicksort(lst, lo, mid - 1);
            quicksort(lst, mid + 1, hi);
        }
    }

    private int partition(List<T> lst, int lo, int hi) {
        T x = lst.get(hi);

        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            if (lst.get(j).compareTo(x) <= 0) {
                i = i + 1;
                swap(lst, i, j);
            }
        }

        swap(lst, i + 1, hi);
        return i + 1;
    }

    private void swap(List<T> lst, int first, int second) {
        T temp = lst.get(first);
        lst.set(first, lst.get(second));
        lst.set(second, temp);
    }
}
