package io.wcygan.concurrent.examples.threadpool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * <pre>
 * Starting task Task 0 at 21:39:55:986
 * Ending task Task 0 at 21:39:56:115 on (pool-1-thread-1) after 129 milliseconds
 * Starting task Task 1 at 21:39:56:122
 * Ending task Task 1 at 21:39:56:247 on (pool-1-thread-1) after 125 milliseconds
 * Starting task Task 2 at 21:39:56:247
 * Ending task Task 2 at 21:39:56:370 on (pool-1-thread-1) after 123 milliseconds
 * Starting task Task 3 at 21:39:56:370
 * Ending task Task 3 at 21:39:56:493 on (pool-1-thread-1) after 123 milliseconds
 * Starting task Task 4 at 21:39:56:493
 * Ending task Task 4 at 21:39:56:615 on (pool-1-thread-1) after 122 milliseconds
 * Starting task Task 5 at 21:39:56:615
 * Ending task Task 5 at 21:39:56:741 on (pool-1-thread-1) after 126 milliseconds
 * Starting task Task 6 at 21:39:56:741
 * Ending task Task 6 at 21:39:56:864 on (pool-1-thread-1) after 123 milliseconds
 * Starting task Task 7 at 21:39:56:864
 * Ending task Task 7 at 21:39:56:986 on (pool-1-thread-1) after 122 milliseconds
 * Starting task Task 8 at 21:39:56:986
 * Ending task Task 8 at 21:39:57:109 on (pool-1-thread-1) after 123 milliseconds
 * Starting task Task 9 at 21:39:57:109
 * Ending task Task 9 at 21:39:57:231 on (pool-1-thread-1) after 122 milliseconds
 * Starting task Task 10 at 21:39:57:231
 * Ending task Task 10 at 21:39:57:354 on (pool-1-thread-1) after 123 milliseconds
 * Starting task Task 11 at 21:39:57:354
 * Ending task Task 11 at 21:39:57:477 on (pool-1-thread-1) after 123 milliseconds
 * Starting task Task 12 at 21:39:57:477
 * Ending task Task 12 at 21:39:57:599 on (pool-1-thread-1) after 122 milliseconds
 * Starting task Task 13 at 21:39:57:600
 * Ending task Task 13 at 21:39:57:723 on (pool-1-thread-1) after 123 milliseconds
 * Starting task Task 14 at 21:39:57:723
 * Ending task Task 14 at 21:39:57:847 on (pool-1-thread-1) after 124 milliseconds
 * Starting task Task 15 at 21:39:57:847
 * Ending task Task 15 at 21:39:57:969 on (pool-1-thread-1) after 122 milliseconds
 * Starting task Task 16 at 21:39:57:970
 * Ending task Task 16 at 21:39:58:092 on (pool-1-thread-1) after 122 milliseconds
 * Starting task Task 17 at 21:39:58:092
 * Ending task Task 17 at 21:39:58:214 on (pool-1-thread-1) after 122 milliseconds
 * Starting task Task 18 at 21:39:58:214
 * Ending task Task 18 at 21:39:58:340 on (pool-1-thread-1) after 126 milliseconds
 * Starting task Task 19 at 21:39:58:340
 * Ending task Task 19 at 21:39:58:465 on (pool-1-thread-1) after 125 milliseconds
 * </pre>
 */
public class ThreadPoolWithSingleThread {

    public static void main(String[] args) {
        int nTasks = 20;
        int fib = 38;

        SingleThreadAccess sta = new SingleThreadAccess();
        for (int i = 0; i < nTasks; i++) {
            sta.invokeLater(new Task(fib, "Task " + i));
        }

        sta.shutdown();
    }
}

class SingleThreadAccess {

    private ThreadPoolExecutor tpe;

    public SingleThreadAccess() {
        tpe = new ThreadPoolExecutor(1, 1, 50000L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    }

    public void invokeLater(Runnable r) {
        tpe.execute(r);
    }

    public void invokeAndWait(Runnable r) throws InterruptedException, ExecutionException {
        FutureTask<?> task = new FutureTask<>(r, null);
        tpe.execute(task);
        task.get();
    }

    public void shutdown() {
        tpe.shutdown();
    }
}

class Task implements Runnable {
    long n;

    String id;

    private long fib(long n) {
        if (n == 0) return 0L;
        if (n == 1) return 1L;
        return fib(n - 1) + fib(n - 2);
    }

    public Task(long n, String id) {
        this.n = n;
        this.id = id;
    }

    public void run() {
        Date d = new Date();
        DateFormat df = new SimpleDateFormat("HH:mm:ss:SSS");
        long startTime = System.currentTimeMillis();
        d.setTime(startTime);
        System.out.println("Starting task " + id + " at " + df.format(d));
        fib(n);
        long endTime = System.currentTimeMillis();
        d.setTime(endTime);
        System.out.println("Ending task " + id + " at " + df.format(d) + " on ("
                + Thread.currentThread().getName() + ") after " + (endTime - startTime) + " milliseconds");
    }
}
