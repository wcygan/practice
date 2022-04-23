package io.wcygan.concurrent.collections;

import io.wcygan.collections.queue.Queue;
import io.wcygan.concurrent.collections.queue.ArrayBlockingQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ArrayBlockingQueueTest {

    @Test
    public void capacityLessThanZero_throwsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ArrayBlockingQueue<>(0));
    }

    @Test
    public void singleThreadedExecution_capacityOne() {
        Queue<Integer> queue = new ArrayBlockingQueue<>(1);
        for (int i = 0; i < 1; i++) {
            Assertions.assertTrue(queue.add(i));
        }
        for (int i = 0; i < 1; i++) {
            Assertions.assertEquals(i, queue.remove());
        }
    }

    @Test
    public void singleThreadedExecution_capacityTwo() {
        Queue<Integer> queue = new ArrayBlockingQueue<>(2);
        for (int i = 0; i < 2; i++) {
            Assertions.assertTrue(queue.add(i));
        }
        for (int i = 0; i < 2; i++) {
            Assertions.assertEquals(i, queue.remove());
        }
    }

    @Test
    public void singleThreadedExecution_capacityThree() {
        Queue<Integer> queue = new ArrayBlockingQueue<>(3);
        for (int i = 0; i < 3; i++) {
            Assertions.assertTrue(queue.add(i));
        }
        for (int i = 0; i < 3; i++) {
            Assertions.assertEquals(i, queue.remove());
        }
    }

    @Test
    public void singleThreadedExecution_capacityOneHundred() {
        int cap = 100;
        Queue<Integer> queue = new ArrayBlockingQueue<>(cap);
        for (int i = 0; i < cap; i++) {
            Assertions.assertTrue(queue.add(i));
        }
        for (int i = 0; i < cap; i++) {
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

    @Test
    public void multiThreadedContention_capacityTwo() throws InterruptedException {
        int n = 50;
        Queue<Integer> queue = new ArrayBlockingQueue<>(2);

        Thread producer = new Thread(() -> {
            for (int i = 0; i < n; i++) {
                Assertions.assertTrue(queue.add(i));
            }
        });

        Thread consumer = new Thread(() -> {
            int maxSoFar = -1;
            for (int i = 0; i < n; i++) {
                Integer got = queue.remove();
                Assertions.assertNotNull(got);
                Assertions.assertTrue(got > maxSoFar);
                maxSoFar = got;
            }
        });

        // Start producing & consumer
        producer.start();
        consumer.start();

        // Wait until finish
        producer.join();
        consumer.join();

        // the queue should be empty at the end...
        Assertions.assertTrue(queue.isEmpty());
        Assertions.assertNull(queue.peek());
    }

    @Test
    public void multiThreadedContention_multipleProducersAndConsumers_capacityTen() throws InterruptedException {
        int t = 20;
        int n = 100;
        Queue<Integer> queue = new ArrayBlockingQueue<>(10);

        // PUT "t * n" items
        // into the queue
        List<Thread> producers = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            final int toAdd = i + 1;
            Thread producer = new Thread(() -> {
                for (int j = 0; j < n; j++) {
                    Assertions.assertTrue(queue.add(toAdd));
                }
            });
            producers.add(producer);
            producer.start();
        }

        // TAKE "t * n" items
        // from the queue
        List<Thread> consumers = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            Thread consumer = new Thread(() -> {
                for (int j = 0; j < n; j++) {
                    Integer got = queue.remove();
                    Assertions.assertNotNull(got);
                }
            });
            consumers.add(consumer);
            consumer.start();
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
