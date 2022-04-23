package io.wcygan.concurrent.examples.futures;

import java.util.concurrent.*;

public class CallableFutureExample {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(3);
        Future<Integer> sum = es.submit(new Sum(10));
        Future<Double> hypotenuse = es.submit(new Hypotenuse(3, 4));
        Future<Integer> factorial = es.submit(new Factorial(5));

        try {
            System.out.println(sum.get());
            System.out.println(hypotenuse.get());
            System.out.println(factorial.get());
        } catch (ExecutionException | InterruptedException e) {
            System.out.println(e.getLocalizedMessage());
        }

        es.shutdown();
    }

    static class Sum implements Callable<Integer> {
        int stop;

        public Sum(int stop) {
            this.stop = stop;
        }

        @Override
        public Integer call() throws Exception {
            int acc = 0;
            for (int i = 0; i <= stop; i++) {
                acc += i;
            }
            return acc;
        }
    }

    static class Hypotenuse implements Callable<Double> {
        double s1, s2;

        public Hypotenuse(double s1, double s2) {
            this.s1 = s1;
            this.s2 = s2;
        }

        @Override
        public Double call() throws Exception {
            return Math.sqrt((s1 * s1) + (s2 * s2));
        }
    }

    static class Factorial implements Callable<Integer> {
        int stop;

        public Factorial(int stop) {
            this.stop = stop;
        }

        @Override
        public Integer call() throws Exception {
            int factorial = 1;
            for (int i = 2; i <= stop; i++) {
                factorial *= i;
            }
            return factorial;
        }
    }
}
