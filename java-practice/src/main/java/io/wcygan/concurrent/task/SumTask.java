package io.wcygan.concurrent.task;

import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * Takes the sum of a list of integers
 *
 * <p>Invoking the task will result in recursively dividing it up into subtasks to be computed
 * concurrently (or in parallel if there are enough processors available)
 */
public class SumTask extends RecursiveTask<Long> {
    int low;
    int high;
    List<Integer> lst;

    SumTask(List<Integer> lst, int low, int high) {
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
            SumTask left = new SumTask(lst, low, mid);
            SumTask right = new SumTask(lst, mid, high);
            left.fork();
            long rightResult = right.compute();
            long leftResult = left.join();
            return leftResult + rightResult;
        }
    }
}
