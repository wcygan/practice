package io.wcygan.algorithms.sorting;

/**
 * Implements a sorting algorithm
 *
 * @param <T> the type of the values to sort
 */
public interface Sorter<T extends Comparable<T>> {
  public void sort(T[] arr);
}
