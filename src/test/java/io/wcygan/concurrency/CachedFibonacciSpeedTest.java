package io.wcygan.concurrency;

import io.wcygan.algorithms.numbers.FibonacciSequence;
import org.junit.jupiter.api.Assertions;

import java.math.BigInteger;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class CachedFibonacciSpeedTest {

  // from https://www.math.net/list-of-fibonacci-numbers
  private static final Integer NUMBER = 25;
  private static final BigInteger ANSWER = new BigInteger(String.valueOf(75025));

  /**
   * This test is inherently nondeterministic, however, with a sufficiently large n, the cached
   * fibonacci generator should be much quicker.
   */
  //  @Test
  // @TODO wcygan: WIP, verify that fast beats slow
  public void cachedFibIsFaster() {
    CountDownLatch ready = new CountDownLatch(2);
    AtomicReference<Long> finishLine = new AtomicReference<>(null);
    Thread fast = new Thread(cachedFib(ready, finishLine));
    Thread slow = new Thread(regularFib(ready, finishLine));

    try {
      // start
      slow.start();
      fast.start();

      // wait
      slow.join();
      fast.join();
    } catch (InterruptedException e) {
      Assertions.fail("Couldn't join fast and slow threads!");
    }

    Assertions.assertEquals(fast.getId(), finishLine.get());
  }

  private Runnable cachedFib(CountDownLatch ready, AtomicReference<Long> finishLine) {
    return () -> {
      ready.countDown();
      try {
        ready.await();
      } catch (InterruptedException e) {
        Assertions.fail("Couldn't await the CountDownLatch!");
      }
      BigInteger answer = new FibonacciSequence().getFibonacciNumberCached(NUMBER);
      Assertions.assertEquals(answer, ANSWER);
      finishLine.compareAndExchange(null, Thread.currentThread().getId());
    };
  }

  private Runnable regularFib(CountDownLatch ready, AtomicReference<Long> finishLine) {
    return () -> {
      ready.countDown();
      try {
        ready.await();
      } catch (InterruptedException e) {
        Assertions.fail("Couldn't await the CountDownLatch!");
      }
      BigInteger answer = FibonacciSequence.getFibonacciNumber(NUMBER);
      Assertions.assertEquals(answer, ANSWER);
      finishLine.compareAndExchange(null, Thread.currentThread().getId());
    };
  }
}
