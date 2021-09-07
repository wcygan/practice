package io.wcygan.algorithms.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort<T extends Comparable<T>> implements Sorter<T> {

  private static final Integer PARALLELISM = 50;

  @Override
  public void sort(List<T> arr) {
    ForkJoinPool pool = new ForkJoinPool(PARALLELISM);
    var task = new ParallelMergeSortTask<>(arr);
    pool.invoke(task);
  }

  private static class ParallelMergeSortTask<T extends Comparable<T>> extends RecursiveAction {

    private final List<T> arr;

    public ParallelMergeSortTask(List<T> arr) {
      this.arr = arr;
    }

    @Override
    protected void compute() {
      if (arr.size() <= 10) {
        new SequentialMergeSort<T>().sort(arr);
        return;
      }

      int mid = arr.size() / 2;
      List<T> left = new ArrayList<>(arr.subList(0, mid));
      List<T> right = new ArrayList<>(arr.subList(mid, arr.size()));

      var leftTask = new ParallelMergeSortTask<>(left);
      var rightTask = new ParallelMergeSortTask<>(right);

      invokeAll(leftTask, rightTask);

      SequentialMergeSort.merge(arr, left, right);
    }
  }
}
