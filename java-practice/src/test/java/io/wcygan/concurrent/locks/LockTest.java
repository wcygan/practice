package io.wcygan.concurrent.locks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    private static final Integer NUM_THREADS = 20;
    private static final Integer ITERATIONS = NUM_THREADS * 2;

    @Test
    public void testLocks() {
        List.of(new ReentrantLock(), new TTASLock()).forEach(this::exercise);
    }

    public void exercise(Lock lock) {
        Counter counter = new Counter();
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        List<Future<Boolean>> futures = new ArrayList<>();

        for (int i = 0; i < ITERATIONS; i++) {
            futures.add(executor.submit(run(lock, counter)));
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        for (var future : futures) {
            try {
                Assertions.assertTrue(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    Callable<Boolean> run(Lock lock, Counter counter) {
        return () -> {
            try {
                /* lock */
                lock.lock();

                /* start critical section */
                counter.up();
                int ct = counter.count();
                counter.down();
                /* end critical section */

                return 1 == ct;
            } finally {
                /* unlock */
                lock.unlock();
            }
        };
    }

    private static class Counter {
        int ct = 0;

        public void up() {
            ct += 1;
        }

        public void down() {
            ct -= 1;
        }

        public int count() {
            return ct;
        }
    }
}
