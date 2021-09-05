package io.wcygan.concurrent;

import io.wcygan.algorithms.numbers.FibonacciSequence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicReference;

public class CachedFibonacciSpeedTest {

  // from https://www.math.net/list-of-fibonacci-numbers
  private static final Integer NUMBER = 42;
  private static final BigInteger ANSWER = new BigInteger("267914296");
  private final Logger LOGGER = LoggerFactory.getLogger(CachedFibonacciSpeedTest.class);

  /**
   * This test is inherently nondeterministic, however, with a sufficiently large n, the cached
   * fibonacci generator should be much quicker.
   */
  @Test
  public void cachedFibIsFaster() {
    AtomicReference<Long> finishLine = new AtomicReference<>(null);
    Thread fast = new Thread(cachedFib(finishLine));
    Thread slow = new Thread(regularFib(finishLine));

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


  private Runnable cachedFib(AtomicReference<Long> finishLine) {
    return () -> {
      BigInteger answer = new FibonacciSequence().getFibonacciNumberCached(NUMBER);
      Assertions.assertEquals(ANSWER, answer);
      finishLine.compareAndExchange(null, Thread.currentThread().getId());
      LOGGER.info("Fast done at " + Instant.now());
    };
  }

  private Runnable regularFib(AtomicReference<Long> finishLine) {
    return () -> {
      BigInteger answer = new FibonacciSequence().getFibonacciNumber(NUMBER);
      Assertions.assertEquals(ANSWER, answer);
      finishLine.compareAndExchange(null, Thread.currentThread().getId());
      LOGGER.info("Slow done at " + Instant.now());
    };
  }
}
