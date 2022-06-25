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
        CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<ExecutorType> winner = new AtomicReference<>(ExecutorType.NONE);
        MultiThreadedExecutor multiThreadedExecutor = MultiThreadedExecutor.create(8);
        SingleThreadedExecutor singleThreadedExecutor = SingleThreadedExecutor.create();

        int sleepTime = 20;
        int count = 10;
        for (int i = 0; i < count; i++) {
            multiThreadedExecutor.execute(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                Waiter.sleepMs(sleepTime);
            });

            singleThreadedExecutor.execute(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                Waiter.sleepMs(sleepTime);
            });
        }

        multiThreadedExecutor.execute(() -> winner.compareAndExchange(ExecutorType.NONE, ExecutorType.MULTI_THREADED));
        singleThreadedExecutor.execute(() -> winner.compareAndExchange(ExecutorType.NONE, ExecutorType.SINGLE_THREADED));

        latch.countDown();

        multiThreadedExecutor.shutdown();
        singleThreadedExecutor.shutdown();

        assertThat(winner.get()).isEqualTo(ExecutorType.MULTI_THREADED);
    }
}
