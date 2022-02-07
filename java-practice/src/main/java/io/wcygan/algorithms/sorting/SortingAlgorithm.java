package io.wcygan.algorithms.sorting;

import java.util.List;

/**
 * Implements a sorting algorithm
 *
 * @param <T> the type of the values to sort
 */
public interface SortingAlgorithm<T extends Comparable<T>> {
    static <T extends Comparable<T>> boolean isSorted(List<T> lst, boolean ascending) {
        for (int currentIndex = 0; currentIndex < lst.size() - 1; currentIndex++) {
            int nextIndex = currentIndex + 1;

            if ((ascending && lst.get(currentIndex).compareTo(lst.get(nextIndex)) > 0)
                    || (!ascending && lst.get(currentIndex).compareTo(lst.get(nextIndex)) < 0)) {
                return false;
            }
        }

        return true;
    }

    void sort(List<T> lst);
}
