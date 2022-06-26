package io.wcygan.concurrent.executors;

public interface Executor {

    /**
     * Submit a new task to the executor
     *
     * @param work the work to be done
     */
    void submit(Runnable work);

    /**
     * Stop accepting new work. Wait until all remaining tasks are finished.
     */
    void shutdown();
}
