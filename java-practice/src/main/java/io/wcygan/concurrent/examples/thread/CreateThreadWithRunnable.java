package io.wcygan.concurrent.examples.thread;

/**
 * Provide a {@link Runnable} to the constructor of {@link Thread}
 */
public class CreateThreadWithRunnable {

    static Runnable runnable() {
        return () -> System.out.println("Hello World!");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(runnable());
        t.start();
        t.join();
    }
}
