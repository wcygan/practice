package io.wcygan.concurrent.executors;

import io.wcygan.common.Waiter;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests {@link MultiThreadedExecutor} and {@link SingleThreadedExecutor} to see which is fastest.
 */
public class ExecutorRaceTest {

    private enum ExecutorType {
        MULTI_THREADED,
        SINGLE_THREADED,
        NONE
    }

    @Test
    public void multiThreadedExecutorIsFasterThanSingleThreadedExecutor() {
        // Spawn the executors
        MultiThreadedExecutor multiThreadedExecutor = MultiThreadedExecutor.create(8);
        SingleThreadedExecutor singleThreadedExecutor = SingleThreadedExecutor.create();

        // Set up the race
        CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<ExecutorType> winner = new AtomicReference<>(ExecutorType.NONE);
        int sleepTime = 20;
        int count = 10;

        // Run waiting tasks on both executors
        for (int i = 0; i < count; i++) {
            multiThreadedExecutor.submit(wait(sleepTime, latch));
            singleThreadedExecutor.submit(wait(sleepTime, latch));
        }

        // Use compareAndExchange to determine the winner
        multiThreadedExecutor.submit(() -> winner.compareAndExchange(ExecutorType.NONE, ExecutorType.MULTI_THREADED));
        singleThreadedExecutor.submit(() -> winner.compareAndExchange(ExecutorType.NONE, ExecutorType.SINGLE_THREADED));

        // Start the race
        latch.countDown();

        // Wait for both executors to finish
        multiThreadedExecutor.shutdown();
        singleThreadedExecutor.shutdown();

        // Assert that the multithreaded executor is faster
        assertThat(winner.get()).isEqualTo(ExecutorType.MULTI_THREADED);
    }

    /**
     * Simulate work by waiting
     *
     * @param ms the amount of milliseconds to wait
     * @return a runnable that waits
     */
    static Runnable wait(int ms, CountDownLatch latch) {
        return () -> {
            try {
                latch.await();
                Waiter.sleepMs(ms);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
