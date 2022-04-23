package io.wcygan.concurrent.collections.misc;

import io.wcygan.algorithms.numbers.Counter;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Adapted from Chapter 15 of Java Concurrency in Practice
 */
@ThreadSafe
public class NonblockingCounter implements Counter {
    AtomicInteger value = new AtomicInteger(STARTING_VALUE);

    @Override
    public int getAndIncrement() {
        int v;
        do {
            v = value.get();
        } while (v != value.compareAndExchange(v, v + 1));
        return v;
    }
}
