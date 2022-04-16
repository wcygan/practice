package io.wcygan.concurrent.speed;

import io.wcygan.algorithms.numbers.FibonacciSequence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class CachedFibonacciSpeedTest {

    // from https://www.math.net/list-of-fibonacci-numbers
    private static final Integer NUMBER = 33;
    private static final BigInteger ANSWER = new BigInteger("3524578");
    private final Logger LOGGER = LoggerFactory.getLogger(CachedFibonacciSpeedTest.class);

    /**
     * This test is inherently nondeterministic, however, with a sufficiently large n, the cached
     * fibonacci generator should be much quicker.
     */
    @Test
    public void cachedFibIsFaster() {
        AtomicReference<Long> finishLine = new AtomicReference<>(null);
        Thread fast = new Thread(fib(() -> new FibonacciSequence().getFibonacciNumberCached(NUMBER), finishLine));
        Thread slow = new Thread(fib(() -> new FibonacciSequence().getFibonacciNumber(NUMBER), finishLine));

        slow.start();
        fast.start();

        try {
            slow.join();
            fast.join();
        } catch (InterruptedException e) {
            Assertions.fail("Couldn't join fast and slow threads!");
        }

        Assertions.assertEquals(fast.getId(), finishLine.get());
    }

    private Runnable fib(Supplier<BigInteger> supplier, AtomicReference<Long> finishLine) {
        return () -> {
            Assertions.assertEquals(ANSWER, supplier.get());
            finishLine.compareAndExchange(null, Thread.currentThread().getId());
        };
    }
}
