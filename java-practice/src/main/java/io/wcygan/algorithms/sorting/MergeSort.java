package io.wcygan.algorithms.sorting;

import java.util.ArrayList;
import java.util.List;

public class MergeSort<T extends Comparable<T>> implements SortingAlgorithm<T> {
    protected static <T extends Comparable<T>> void mergeSort(List<T> lst) {
        if (lst.size() <= 1) {
            return;
        }

        int mid = lst.size() / 2;
        List<T> left = new ArrayList<>(lst.subList(0, mid));
        List<T> right = new ArrayList<>(lst.subList(mid, lst.size()));

        mergeSort(left);
        mergeSort(right);
        merge(lst, left, right);
    }

    protected static <T extends Comparable<T>> void merge(List<T> original, List<T> left, List<T> right) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareTo(right.get(j)) < 0) {
                original.set(k, left.get(i));
                i++;
            } else {
                original.set(k, right.get(j));
                j++;
            }
            k++;
        }

        while (i < left.size()) {
            original.set(k, left.get(i));
            i++;
            k++;
        }

        while (j < right.size()) {
            original.set(k, right.get(j));
            j++;
            k++;
        }
    }

    @Override
    public void sort(List<T> lst) {
        mergeSort(lst);
    }
}
