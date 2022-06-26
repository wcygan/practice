package io.wcygan.concurrent.executors;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A simple executor that runs tasks in parallel.
 */
public class MultiThreadedExecutor implements Executor {

    private static final Runnable POISON = () -> {
    };
    BlockingQueue<Runnable> work;
    private final AtomicBoolean acceptingNewWork;
    private final HashMap<String, Worker> threadPool;

    private MultiThreadedExecutor(BlockingQueue<Runnable> work, int threadCount) {
        this.work = work;
        this.acceptingNewWork = new AtomicBoolean(true);
        this.threadPool = new HashMap<>();

        for (int i = 0; i < threadCount; i++) {
            Worker worker = new Worker(work);
            threadPool.put(worker.getName(), worker);
            worker.start();
        }
    }

    /**
     * Create a thread pool with the amount of available processors
     *
     * @return an executor
     */
    public static MultiThreadedExecutor create() {
        return new MultiThreadedExecutor(
                new LinkedBlockingQueue<>(),
                Runtime.getRuntime().availableProcessors()
        );
    }

    /**
     * Create a thread pool with a specified amount of threads
     *
     * @param threadCount the amount of threads
     * @return an executor
     */
    public static MultiThreadedExecutor create(int threadCount) {
        return new MultiThreadedExecutor(
                new LinkedBlockingQueue<>(),
                threadCount
        );
    }

    /**
     * Submit work
     *
     * @param work the task to execute
     */
    @Override
    public void submit(Runnable work) {
        if (acceptingNewWork.get()) {
            try {
                this.work.put(work);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * Stop accepting new work and wait for all tasks to finish
     */
    @Override
    public void shutdown() {
        acceptingNewWork.set(false);
        try {
            work.put(POISON);
            for (Worker worker : threadPool.values()) {
                worker.join();
            }
        } catch (InterruptedException e) {
            System.exit(999);
        }
    }

    /**
     * Worker class for the thread pool
     */
    static class Worker extends Thread {

        BlockingQueue<Runnable> work;

        public Worker(BlockingQueue<Runnable> work) {
            this.work = work;
        }

        /**
         * Run the worker and execute tasks until the poison pill is received
         */
        @Override
        public void run() {
            super.run();

            while (true) {
                try {
                    Runnable runnable = work.take();
                    if (runnable == POISON) {
                        work.put(POISON);
                        break;
                    }

                    runnable.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
