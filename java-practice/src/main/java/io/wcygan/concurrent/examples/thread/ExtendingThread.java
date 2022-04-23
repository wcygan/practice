package io.wcygan.concurrent.examples.thread;

/**
 * Override {@link Thread#run()}
 */
public class ExtendingThread {

    static class Hello extends Thread {
        @Override
        public void run() {
            super.run();
            System.out.println("Hello World!");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread hello = new Hello();
        hello.start();
        hello.join();
    }
}
