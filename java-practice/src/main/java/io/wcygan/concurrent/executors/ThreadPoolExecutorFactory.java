package io.wcygan.concurrent.executors;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorFactory {
    private static final Long KEEP_ALIVE = 50000L;

    public static ThreadPoolExecutor create(int threads) {
        return new ThreadPoolExecutor(threads, threads, KEEP_ALIVE, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    }
}
