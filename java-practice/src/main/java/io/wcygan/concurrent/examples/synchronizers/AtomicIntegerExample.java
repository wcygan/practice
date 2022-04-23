package io.wcygan.concurrent.examples.synchronizers;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * Notice how the counter has each number from (1..10) appearing only once?
 *
 * This is achieved through the "Compare and Swap" / CAS operation:
 * <a href="https://en.wikipedia.org/wiki/Compare-and-swap">https://en.wikipedia.org/wiki/Compare-and-swap</a>
 *
 * {@code
 * Thread pool-1-thread-1 incremented the counter to 3
 * Thread pool-1-thread-3 incremented the counter to 2
 * Thread pool-1-thread-2 incremented the counter to 1
 * Thread pool-1-thread-3 incremented the counter to 4
 * Thread pool-1-thread-2 incremented the counter to 6
 * Thread pool-1-thread-1 incremented the counter to 5
 * Thread pool-1-thread-2 incremented the counter to 8
 * Thread pool-1-thread-1 incremented the counter to 9
 * Thread pool-1-thread-2 incremented the counter to 10
 * Thread pool-1-thread-3 incremented the counter to 7
 * }
 */
public class AtomicIntegerExample {
    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    public static void main(String[] args) {
        var es = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 10; i++) {
            es.submit(increment);
        }

        es.shutdown();
    }

    static Runnable increment = () -> {
        int v = 0;

        do {
            v = COUNTER.get();
        } while (v != COUNTER.compareAndExchange(v, v + 1));

        System.out.println("Thread " + Thread.currentThread().getName() + " incremented the counter to " + (v + 1));
    };
}
