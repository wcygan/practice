package io.wcygan.concurrent.examples.synchronizers;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 *
 *              [ Bathroom 1 ]    [ Bathroom 2 ]
 *
 *  threads = { Thread 1, Thread 2, Thread 3, Thread 4, Thread 5 }
 *
 *  All of the threads want to go to use bathroom, but there are only two bathrooms!
 *
 *  So, each will try to acquire a permit, use the bathroom, and release the permit once finished...
 *
 *
 * Here is some example output:
 *
 * {@code
 * pool-1-thread-1 is finished using the bathroom with ticket 0. There are 1 bathrooms available!
 * pool-1-thread-2 is finished using the bathroom with ticket 1. There are 0 bathrooms available!
 * pool-1-thread-1 is finished using the bathroom with ticket 3. There are 0 bathrooms available!
 * pool-1-thread-3 is finished using the bathroom with ticket 2. There are 0 bathrooms available!
 * pool-1-thread-1 is finished using the bathroom with ticket 5. There are 0 bathrooms available!
 * pool-1-thread-3 is finished using the bathroom with ticket 6. There are 0 bathrooms available!
 * pool-1-thread-3 is finished using the bathroom with ticket 7. There are 0 bathrooms available!
 * pool-1-thread-2 is finished using the bathroom with ticket 4. There are 1 bathrooms available!
 * pool-1-thread-3 is finished using the bathroom with ticket 9. There are 0 bathrooms available!
 * pool-1-thread-2 is finished using the bathroom with ticket 10. There are 0 bathrooms available!
 * pool-1-thread-3 is finished using the bathroom with ticket 11. There are 0 bathrooms available!
 * pool-1-thread-2 is finished using the bathroom with ticket 12. There are 0 bathrooms available!
 * pool-1-thread-3 is finished using the bathroom with ticket 13. There are 0 bathrooms available!
 * pool-1-thread-2 is finished using the bathroom with ticket 14. There are 0 bathrooms available!
 * pool-1-thread-3 is finished using the bathroom with ticket 15. There are 0 bathrooms available!
 * pool-1-thread-2 is finished using the bathroom with ticket 16. There are 0 bathrooms available!
 * pool-1-thread-3 is finished using the bathroom with ticket 17. There are 0 bathrooms available!
 * pool-1-thread-2 is finished using the bathroom with ticket 18. There are 0 bathrooms available!
 * pool-1-thread-3 is finished using the bathroom with ticket 19. There are 0 bathrooms available!
 * pool-1-thread-1 is finished using the bathroom with ticket 8. There are 0 bathrooms available!
 * }
 * </pre>
 *
 */
public class SemaphoreExample {

    private static final Integer BATHROOM_COUNT = 2;
    private static final AtomicInteger BATHROOMS_AVAILABLE = new AtomicInteger(BATHROOM_COUNT);

    public static void main(String[] args) {

        /*
         * The semaphore will only allow threads who own a permit into a critical section of code
         */
        Semaphore semaphore = new Semaphore(BATHROOM_COUNT);

        var e = ForkJoinPool.commonPool();

        for (int i = 0; i < 20; i++) {
            e.execute(occupyTheBathroom(semaphore, i));
        }

        e.shutdown();
    }

    static Runnable occupyTheBathroom(Semaphore semaphore, int ticket) {
        return () -> {
            try {
                // get the permit
                semaphore.acquire();
                int curr_count = BATHROOMS_AVAILABLE.decrementAndGet();

                // do the stuff
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + " is finished using the bathroom with ticket "
                        + ticket + ". There are " + curr_count + " bathrooms available!");
            } catch (InterruptedException e) {
                throw new RuntimeException("rip lmao!");
            } finally {
                // release the permit
                BATHROOMS_AVAILABLE.incrementAndGet();
                semaphore.release();
            }
        };
    }
}
