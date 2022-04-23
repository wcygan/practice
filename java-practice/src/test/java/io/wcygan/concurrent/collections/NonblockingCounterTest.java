package io.wcygan.concurrent.collections;

import io.wcygan.algorithms.numbers.Counter;
import io.wcygan.concurrent.collections.misc.NonblockingCounter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class NonblockingCounterTest {

    private static final Integer NUM_THREADS = 32;

    @Test
    public void testTaskExecutor() {
        Counter counter = new NonblockingCounter();

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        Callable<List<Integer>> incrementThreeTimes =
                () -> List.of(counter.getAndIncrement(), counter.getAndIncrement(), counter.getAndIncrement());

        List<Future<List<Integer>>> tasks = new ArrayList<>();
        for (int i = 0; i < NUM_THREADS; i++) {
            tasks.add(executor.submit(incrementThreeTimes));
        }

        Set<Integer> values = new HashSet<>();
        tasks.forEach(t -> {
            try {
                values.addAll(t.get());
            } catch (InterruptedException | ExecutionException e) {
                Assertions.fail("Couldn't add to the computed values set");
            }
        });

        int max = 3 * NUM_THREADS;
        Assertions.assertEquals(
                max, IntStream.range(0, max).filter(values::contains).count());
    }

    @Test
    public void testThreads() {
        Counter counter = new NonblockingCounter();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < NUM_THREADS; i++) {
            Thread t = new Thread(counter::getAndIncrement);
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Assertions.fail("Failed to join thread " + t.getName());
            }
        }

        Assertions.assertEquals(NUM_THREADS, counter.getAndIncrement());
    }
}
