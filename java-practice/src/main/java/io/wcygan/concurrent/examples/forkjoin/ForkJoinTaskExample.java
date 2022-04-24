package io.wcygan.concurrent.examples.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class ForkJoinTaskExample {
    static ForkJoinPool pool = ForkJoinPool.commonPool();

    public static void main(String[] args) {

        double[] nums = new double[10000];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = i;
        }

        for (int i = 0; i < 10; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();

        SqrtTransform task = new SqrtTransform(nums, 0, nums.length);

        pool.invoke(task);

        for (int i = 0; i < 10; i++) {
            System.out.format("%.4f ", nums[i]);
        }
        System.out.println();

        pool.awaitQuiescence(1, TimeUnit.DAYS);
    }

    static class SqrtTransform extends RecursiveAction {

        final int threshold = 1000;

        double[] data;

        int start, end;

        public SqrtTransform(double[] data, int start, int end) {
            this.data = data;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if ((end - start) < threshold) {
                for (int i = start; i < end; i++) {
                    data[i] = Math.sqrt(data[i]);
                }
            } else {
                int mid = (start + end) / 2;
                invokeAll(new SqrtTransform(data, start, mid), new SqrtTransform(data, mid, end));
            }
        }
    }
}
