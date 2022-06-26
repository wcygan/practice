package io.wcygan.concurrent.executors;

import io.wcygan.common.Waiter;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

public class MultiThreadedExecutorTest {
    @Test
    public void test() {
        int count = 1000;
        AtomicInteger counter = new AtomicInteger(0);

        MultiThreadedExecutor executor = MultiThreadedExecutor.create(12);

        for (int i = 0; i < count; i++) {
            executor.submit(() -> {
                Waiter.sleepMs(5);
                counter.incrementAndGet();
            });
        }

        executor.shutdown();

        assertThat(counter.get()).isEqualTo(count);
    }
}
