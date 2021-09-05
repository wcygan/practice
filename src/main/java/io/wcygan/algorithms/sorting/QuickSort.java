package io.wcygan.algorithms.sorting;

/**
 * Implements QuickSort adapted from CLRS Chapter 7
 *
 * @param <T> the type of the values to sort
 */
public class QuickSort<T extends Comparable<T>> implements Sorter<T> {
  // TODO implement a quickcheck test that generates arrays of random sizes

  @Override
  public void sort(T[] arr) {
    quicksort(arr, 0, arr.length - 1);
  }

  private void quicksort(T[] arr, int p, int r) {
    if (p < r) {
      int q = partition(arr, p, r);
      quicksort(arr, p, q - 1);
      quicksort(arr, q + 1, r);
    }
  }

  private int partition(T[] arr, int p, int r) {
    T x = arr[r];
    int i = p - 1;
    for (int j = p; j < r; j++) {

      if (arr[j].compareTo(x) <= 0) {
        i = i + 1;
        swap(arr, i, j);
      }
    }
    swap(arr, i + 1, r);
    return i + 1;
  }

  private void swap(T[] arr, int first, int second) {
    T a = arr[first];
    arr[first] = arr[second];
    arr[second] = a;
  }
}
