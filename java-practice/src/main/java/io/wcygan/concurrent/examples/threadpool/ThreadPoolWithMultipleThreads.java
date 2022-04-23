package io.wcygan.concurrent.examples.threadpool;

import java.util.concurrent.*;

/**
 * <pre>
 * Starting task Task 7 at 21:40:24:993
 * Starting task Task 0 at 21:40:24:993
 * Starting task Task 1 at 21:40:24:992
 * Starting task Task 5 at 21:40:24:992
 * Starting task Task 2 at 21:40:24:993
 * Starting task Task 6 at 21:40:24:993
 * Starting task Task 8 at 21:40:24:993
 * Starting task Task 9 at 21:40:24:993
 * Starting task Task 4 at 21:40:24:992
 * Starting task Task 3 at 21:40:24:993
 * Ending task Task 9 at 21:40:25:172 on (pool-1-thread-10) after 179 milliseconds
 * Ending task Task 1 at 21:40:25:178 on (pool-1-thread-2) after 186 milliseconds
 * Starting task Task 10 at 21:40:25:178
 * Starting task Task 11 at 21:40:25:178
 * Ending task Task 0 at 21:40:25:176 on (pool-1-thread-1) after 183 milliseconds
 * Ending task Task 3 at 21:40:25:172 on (pool-1-thread-4) after 179 milliseconds
 * Starting task Task 13 at 21:40:25:179
 * Ending task Task 8 at 21:40:25:176 on (pool-1-thread-9) after 183 milliseconds
 * Starting task Task 14 at 21:40:25:179
 * Starting task Task 12 at 21:40:25:178
 * Ending task Task 4 at 21:40:25:192 on (pool-1-thread-5) after 200 milliseconds
 * Starting task Task 15 at 21:40:25:192
 * Ending task Task 5 at 21:40:25:201 on (pool-1-thread-6) after 209 milliseconds
 * Starting task Task 16 at 21:40:25:201
 * Ending task Task 2 at 21:40:25:210 on (pool-1-thread-3) after 217 milliseconds
 * Starting task Task 17 at 21:40:25:210
 * Ending task Task 7 at 21:40:25:228 on (pool-1-thread-8) after 235 milliseconds
 * Starting task Task 18 at 21:40:25:228
 * Ending task Task 6 at 21:40:25:252 on (pool-1-thread-7) after 259 milliseconds
 * Starting task Task 19 at 21:40:25:253
 * Ending task Task 10 at 21:40:25:351 on (pool-1-thread-10) after 173 milliseconds
 * Ending task Task 11 at 21:40:25:385 on (pool-1-thread-2) after 207 milliseconds
 * Ending task Task 14 at 21:40:25:388 on (pool-1-thread-9) after 209 milliseconds
 * Ending task Task 12 at 21:40:25:392 on (pool-1-thread-1) after 214 milliseconds
 * Ending task Task 13 at 21:40:25:398 on (pool-1-thread-4) after 219 milliseconds
 * Ending task Task 15 at 21:40:25:398 on (pool-1-thread-5) after 206 milliseconds
 * Ending task Task 16 at 21:40:25:410 on (pool-1-thread-6) after 209 milliseconds
 * Ending task Task 17 at 21:40:25:414 on (pool-1-thread-3) after 204 milliseconds
 * Ending task Task 18 at 21:40:25:429 on (pool-1-thread-8) after 201 milliseconds
 * Ending task Task 19 at 21:40:25:433 on (pool-1-thread-7) after 180 milliseconds
 * </pre>
 */
public class ThreadPoolWithMultipleThreads {

    public static void main(String[] args) {
        int nTasks = 20;
        int fib = 38;

        MultipleThreadAccess sta = new MultipleThreadAccess();
        for (int i = 0; i < nTasks; i++) {
            sta.invokeLater(new Task(fib, "Task " + i));
        }

        sta.shutdown();
    }
}

class MultipleThreadAccess {

    private ThreadPoolExecutor tpe;

    public MultipleThreadAccess() {
        int cpuCount = Runtime.getRuntime().availableProcessors();
        tpe = new ThreadPoolExecutor(cpuCount, cpuCount, 50000L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
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