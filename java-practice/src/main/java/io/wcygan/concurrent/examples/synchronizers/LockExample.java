package io.wcygan.concurrent.examples.synchronizers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Locks are like a {@link java.util.concurrent.Semaphore} who has one permit
 *
 * There are specialized versions of {@link java.util.concurrent.locks.Lock} that allow
 * multiple readers but a single writer, see {@link java.util.concurrent.locks.ReentrantReadWriteLock}
 *
 * <pre>
 *     Here we can model a scenario where there are multiple people waiting
 *     to use a single bathroom. Only one person can use the bathroom at a time
 *
 *
 * {@code
 * Thread pool-1-thread-3 is using the bathroom
 * Thread pool-1-thread-3 is finished with the bathroom
 * Thread pool-1-thread-6 is using the bathroom
 * Thread pool-1-thread-6 is finished with the bathroom
 * Thread pool-1-thread-4 is using the bathroom
 * Thread pool-1-thread-4 is finished with the bathroom
 * Thread pool-1-thread-5 is using the bathroom
 * Thread pool-1-thread-5 is finished with the bathroom
 * Thread pool-1-thread-2 is using the bathroom
 * Thread pool-1-thread-2 is finished with the bathroom
 * Thread pool-1-thread-1 is using the bathroom
 * Thread pool-1-thread-1 is finished with the bathroom
 * Thread pool-1-thread-7 is using the bathroom
 * Thread pool-1-thread-7 is finished with the bathroom
 * Thread pool-1-thread-8 is using the bathroom
 * Thread pool-1-thread-8 is finished with the bathroom
 * Thread pool-1-thread-9 is using the bathroom
 * Thread pool-1-thread-9 is finished with the bathroom
 * Thread pool-1-thread-10 is using the bathroom
 * Thread pool-1-thread-10 is finished with the bathroom
 * }
 *
 * See how there is nobody using the bathroom at the same time?
 *
 * That is because the {@link Lock} provides mutually exclusive access to the "critical section"
 * (e.g., using the bathroom)
 * </pre>
 */
public class LockExample {

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            es.submit(useBathroom);
        }

        es.shutdown();
    }

    static Runnable useBathroom = () -> {
        lock.lock();
        try {
            System.out.println("Thread " + Thread.currentThread().getName() + " is using the bathroom");
            Thread.sleep(50);
            System.out.println("Thread " + Thread.currentThread().getName() + " is finished with the bathroom");
            Thread.sleep(50);
        } catch (InterruptedException e) {
            System.out.println("rip lol");
        } finally {
            lock.unlock();
        }
    };
}
