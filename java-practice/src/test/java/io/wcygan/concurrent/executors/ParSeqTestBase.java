package io.wcygan.concurrent.executors;

import com.linkedin.parseq.Engine;
import com.linkedin.parseq.EngineBuilder;
import com.linkedin.parseq.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ParSeqTestBase {
    protected Engine _engine;
    protected ScheduledExecutorService _scheduler;

    @BeforeEach
    public void setUp() {
        final int numCores = Runtime.getRuntime().availableProcessors();
        _scheduler = Executors.newScheduledThreadPool(numCores + 1);
        EngineBuilder engineBuilder =
                new EngineBuilder().setTaskExecutor(_scheduler).setTimerScheduler(_scheduler);
        _engine = engineBuilder.build();
    }

    @AfterEach
    public void tearDown() throws Exception {
        tearDown(200, TimeUnit.MILLISECONDS);
    }

    public void tearDown(final int time, final TimeUnit unit) throws Exception {
        _engine.shutdown();
        _engine.awaitTermination(time, unit);
        _engine = null;
        _scheduler.shutdownNow();
        _scheduler = null;
    }

    public <T> T runAndWait(Task<T> task) {
        try {
            _engine.run(task);
            boolean result = task.await(5, TimeUnit.SECONDS);
            if (!result) {
                throw new AssertionError("Expected task result to be successful");
            }
            return task.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
