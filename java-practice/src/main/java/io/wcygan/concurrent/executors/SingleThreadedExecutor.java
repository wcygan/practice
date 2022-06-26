package io.wcygan.concurrent.executors;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class SingleThreadedExecutor implements Executor {

    private static final Runnable POISON = () -> {
    };
    BlockingQueue<Runnable> work;
    private final AtomicBoolean acceptingNewWork;
    private final Worker worker;

    private SingleThreadedExecutor(BlockingQueue<Runnable> work) {
        this.work = work;
        this.acceptingNewWork = new AtomicBoolean(true);
        this.worker = new Worker();
        this.worker.start();
    }

    public static SingleThreadedExecutor create() {
        return new SingleThreadedExecutor(new LinkedBlockingQueue<>());
    }

    @Override
    public void submit(Runnable work) {
        if (acceptingNewWork.get()) {
            this.work.add(work);
        }
    }

    @Override
    public void shutdown() {
        acceptingNewWork.set(false);
        try {
            work.put(POISON);
            this.worker.join();
        } catch (InterruptedException e) {
            System.exit(999);
        }
    }

    class Worker extends Thread {

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
