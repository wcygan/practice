package io.wcygan.algorithms.sorting;

import java.util.List;

/**
 * Implements QuickSort adapted from CLRS Chapter 7
 *
 * @param <T> the type of the values to sort
 */
public class QuickSort<T extends Comparable<T>> implements Sorter<T> {

  @Override
  public void sort(List<T> lst) {
    quicksort(lst, 0, lst.size() - 1);
  }

  private void quicksort(List<T> lst, int p, int r) {
    if (p < r) {
      int q = partition(lst, p, r);
      quicksort(lst, p, q - 1);
      quicksort(lst, q + 1, r);
    }
  }

  private int partition(List<T> lst, int p, int r) {
    T x = lst.get(r);
    int i = p - 1;
    for (int j = p; j < r; j++) {

      if (lst.get(j).compareTo(x) <= 0) {
        i = i + 1;
        swap(lst, i, j);
      }
    }
    swap(lst, i + 1, r);
    return i + 1;
  }

  private void swap(List<T> lst, int first, int second) {
    T a = lst.get(first);
    lst.set(first, lst.get(second));
    lst.set(second, a);
  }
}
