package io.wcygan.concurrent.examples.thread;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;


public class Bag {

    private static final Integer THREAD_COUNT = 10;
    private static final Integer MAX_COUNT = 100;
    private static final Packet POISON = new Packet(Integer.MIN_VALUE);

    public static void main(String[] args) {
        // array blocking queue
        ArrayBlockingQueue<Packet> q = new ArrayBlockingQueue<>(1);
        q.add(new Packet(0));

        // threads are all polling until threshold
        List<Consumer> consumers = new ArrayList<>();

        for (int i = 0; i < THREAD_COUNT; i++) {
            var c = new Consumer(q);
            consumers.add(c);
            System.out.println("Started " + c.getName());
            c.start();
        }

        try {
            for (var t : consumers) {
                t.join();
            }
        } catch (Exception ignored) {
        }

        System.out.println("done");
    }

    static class Packet {
        int count;

        public Packet(int count) {
            this.count = count;
        }
    }

    static class Consumer extends Thread {

        ArrayBlockingQueue<Packet> q;

        public Consumer(ArrayBlockingQueue<Packet> q) {
            this.q = q;
        }

        @Override
        public synchronized void run() {
            while (true) {
                Packet got = null;
                try {
                    got = q.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (got == POISON || got == null) {
                    System.out.println("Thread " + Thread.currentThread().getName() + " was poisoned");
                    return;
                }

                System.out.println("Thread " + Thread.currentThread().getName() + " got " + got.count);
                got.count = got.count + 1;


                Packet next = got.count < MAX_COUNT ? got : POISON;
                try {
                    q.put(next);
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
