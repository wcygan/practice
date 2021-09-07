package io.wcygan.algorithms.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort<T extends Comparable<T>> implements Sorter<T> {

  private static final Integer PARALLELISM = 50;

  @Override
  public void sort(List<T> lst) {
    ForkJoinPool pool = new ForkJoinPool(PARALLELISM);
    var task = new ParallelMergeSortTask<>(lst);
    pool.invoke(task);
  }

  private static class ParallelMergeSortTask<T extends Comparable<T>> extends RecursiveAction {

    private final List<T> lst;

    public ParallelMergeSortTask(List<T> lst) {
      this.lst = lst;
    }

    @Override
    protected void compute() {
      if (lst.size() <= 10) {
        new SequentialMergeSort<T>().sort(lst);
        return;
      }

      int mid = lst.size() / 2;
      List<T> left = new ArrayList<>(lst.subList(0, mid));
      List<T> right = new ArrayList<>(lst.subList(mid, lst.size()));

      var leftTask = new ParallelMergeSortTask<>(left);
      var rightTask = new ParallelMergeSortTask<>(right);

      invokeAll(leftTask, rightTask);

      SequentialMergeSort.merge(lst, left, right);
    }
  }
}
