package io.wcygan.concurrent.executors;

import io.wcygan.common.Waiter;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class SingleThreadedExecutorTest {

    @Test
    public void happyPath() {
        AtomicBoolean flag = new AtomicBoolean(false);

        Executor e = SingleThreadedExecutor.create();

        e.submit(() -> {
            Waiter.sleepMs(50);
            flag.set(true);
        });

        e.shutdown();

        assertThat(flag.get()).isTrue();
    }

    @Test
    public void threeTasksFinish() {
        int max = 3;
        List<AtomicBoolean> values = IntStream.range(0, max)
                .mapToObj(x -> new AtomicBoolean(false))
                .toList();

        Executor e = SingleThreadedExecutor.create();

        for (int i = 0; i < max; i++) {
            int index = i;
            e.submit(() -> {
                Waiter.sleepMs(25);
                values.get(index).set(true);
            });
        }

        e.shutdown();

        for (AtomicBoolean v : values) {
            assertThat(v.get()).isTrue();
        }
    }
}
