package io.wcygan.concurrent.collections;

import io.wcygan.collections.stack.Stack;
import io.wcygan.concurrent.collections.stack.NonblockingStack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class NonblockingStackTest {

    private static Integer NUM_THREADS = 10;

    @Test
    public void pushThreeTimesPopThreeTimes() {
        List<Integer> toAdd = new ArrayList<>(List.of(1, 2, 3));
        Stack<Integer> stack = new NonblockingStack<>();
        Assertions.assertNull(stack.peek());

        toAdd.forEach(stack::push);
        Assertions.assertEquals(toAdd.get(toAdd.size() - 1), stack.peek());
        Collections.reverse(toAdd);

        Assertions.assertEquals(toAdd.size(), stack.size());
        toAdd.forEach(element -> Assertions.assertEquals(element, stack.pop()));
        Assertions.assertEquals(0, stack.size());
        Assertions.assertNull(stack.peek());
    }

    @Test
    public void testMultipleThreads() {
        int range = 25;
        Stack<Integer> stack = new NonblockingStack<>();

        Runnable pushRange = () -> {
            IntStream.range(0, range).forEach(stack::push);
        };

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < NUM_THREADS; i++) {
            Thread t = new Thread(pushRange);
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

        Assertions.assertEquals(range * NUM_THREADS, stack.size());
        Assertions.assertEquals(range - 1, stack.peek());
    }
}
