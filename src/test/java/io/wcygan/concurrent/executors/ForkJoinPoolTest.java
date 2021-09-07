package io.wcygan.concurrent.executors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class ForkJoinPoolTest {

  public static Integer LEFT_BOUND = 0;
  public static Integer RIGHT_BOUND = 1000;

  @Test
  public void itWorks() {
    ForkJoinPool pool = new ForkJoinPool(10);
    List<Integer> lst = IntStream.range(LEFT_BOUND, RIGHT_BOUND).boxed().toList();
    Long expectedValue = LongStream.range(LEFT_BOUND, RIGHT_BOUND).sum();
    Assertions.assertEquals(expectedValue, pool.invoke(new Sum(lst, 0, lst.size())));
  }

  static class Sum extends RecursiveTask<Long> {
    int low;
    int high;
    List<Integer> lst;

    Sum(List<Integer> lst, int low, int high) {
      this.lst = lst;
      this.low = low;
      this.high = high;
    }

    protected Long compute() {
      if (high - low <= 10) {
        long sum = 0;
        for (int i = low; i < high; ++i) sum += lst.get(i);
        return sum;
      } else {
        int mid = low + (high - low) / 2;
        Sum left = new Sum(lst, low, mid);
        Sum right = new Sum(lst, mid, high);
        left.fork();
        long rightResult = right.compute();
        long leftResult = left.join();
        return leftResult + rightResult;
      }
    }
  }
}
