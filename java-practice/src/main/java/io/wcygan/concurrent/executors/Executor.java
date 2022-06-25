package io.wcygan.concurrent.executors;

public interface Executor {

    /**
     * Submit a new task to the executor
     *
     * @param runnable the task to execute
     */
    void execute(Runnable runnable);

    /**
     * Stop accepting new work. Wait until all remaining tasks are finished.
     */
    void shutdown();
}
