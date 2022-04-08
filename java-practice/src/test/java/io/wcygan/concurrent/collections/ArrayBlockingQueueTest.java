package io.wcygan.concurrent.collections;

import io.wcygan.collections.queue.Queue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ArrayBlockingQueueTest {
    private static final Integer ITERATIONS = 25;

    @Test
    public void capacityLessThanZero_throwsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ArrayBlockingQueue<>(0));
    }

    @Test
    public void singleThreadedExecution_capacityOne() {
        Queue<Integer> queue = new ArrayBlockingQueue<>(1);
        for (int i = 0; i < ITERATIONS; i++) {
            Assertions.assertTrue(queue.add(i));
            Assertions.assertEquals(i, queue.remove());
        }
    }

    @Test
    public void singleThreadedExecution_capacityTwo() {
        Queue<Integer> queue = new ArrayBlockingQueue<>(2);
        for (int i = 0; i < ITERATIONS; i++) {
            Assertions.assertTrue(queue.add(i));
            Assertions.assertEquals(i, queue.remove());
        }
    }

    @Test
    public void singleThreadedExecution_capacityTree() {
        Queue<Integer> queue = new ArrayBlockingQueue<>(3);
        for (int i = 0; i < ITERATIONS; i++) {
            Assertions.assertTrue(queue.add(i));
            Assertions.assertEquals(i, queue.remove());
        }
    }

    @Test
    public void multiThreadedExecution_capacityOne_finishes() throws InterruptedException {
        int threadCount = 10;
        Queue<Integer> queue = new ArrayBlockingQueue<>(1);

        List<Thread> producers = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            final int toAdd = i;
            Thread t = new Thread(() -> Assertions.assertTrue(queue.add(toAdd)));
            producers.add(t);
            t.start();
        }

        List<Thread> consumers = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            Thread t = new Thread(() -> Assertions.assertNotNull(queue.remove()));
            consumers.add(t);
            t.start();
        }

        // wait for all the producers to finish
        for (Thread producer : producers) {
            producer.join();
        }

        // wait for all the producers to finish
        for (Thread consumer : consumers) {
            consumer.join();
        }

        Assertions.assertTrue(queue.isEmpty());
        Assertions.assertNull(queue.peek());
    }

    @Test
    public void multiThreadedExecution_capacityThree_finishes() throws InterruptedException {
        int threadCount = 50;
        Queue<Integer> queue = new ArrayBlockingQueue<>(3);

        // "put" items into the queue from various threads
        List<Thread> producers = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            final int toAdd = i;
            Thread t = new Thread(() -> Assertions.assertTrue(queue.add(toAdd)));
            producers.add(t);
            t.start();
        }

        // "take" items from the queue from various threads
        List<Thread> consumers = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            Thread t = new Thread(() -> Assertions.assertNotNull(queue.remove()));
            consumers.add(t);
            t.start();
        }

        // wait for all the producers to finish
        for (Thread producer : producers) {
            producer.join();
        }

        // wait for all the producers to finish
        for (Thread consumer : consumers) {
            consumer.join();
        }

        Assertions.assertTrue(queue.isEmpty());
        Assertions.assertNull(queue.peek());
    }
}
