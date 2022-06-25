package io.wcygan.concurrent.executors;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class SingleThreadedExecutor implements Executor {

    private static final Runnable POISON = () -> {
    };
    BlockingQueue<Runnable> work;
    private final AtomicBoolean acceptingNewWork;
    private final Thread worker;

    private SingleThreadedExecutor(BlockingQueue<Runnable> work) {
        this.work = work;
        this.acceptingNewWork = new AtomicBoolean(true);
        this.worker = new Thread(singleThreadedWorker());
        this.worker.start();
    }

    public static SingleThreadedExecutor create() {
        return new SingleThreadedExecutor(new LinkedBlockingQueue<>());
    }

    @Override
    public void execute(Runnable runnable) {
        if (acceptingNewWork.get()) {
            work.add(runnable);
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

    Runnable singleThreadedWorker() {
        return () -> {
            while (true) {
                try {
                    Runnable runnable = work.take();
                    if (runnable == POISON) {
                        break;
                    }

                    runnable.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}
