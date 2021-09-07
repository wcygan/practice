package io.wcygan.algorithms.sorting;

import java.util.ArrayList;
import java.util.List;

public class SequentialMergeSort<T extends Comparable<T>> implements Sorter<T> {
  @Override
  public void sort(List<T> arr) {
    mergeSort(arr);
  }

  public void mergeSort(List<T> arr) {
    if (arr.size() <= 1) {
      return;
    }

    int mid = arr.size() / 2;
    List<T> left = new ArrayList<>(arr.subList(0, mid));
    List<T> right = new ArrayList<>(arr.subList(mid, arr.size()));

    mergeSort(left);
    mergeSort(right);
    merge(arr, left, right);
  }

  public void merge(List<T> original, List<T> left, List<T> right) {
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
}
