package io.wcygan.common;

public class Waiter {
    public static void sleepMs(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void spinMs(long ms) {
        long start = System.nanoTime();
        while (System.nanoTime() - start < ms) {}
    }
}
