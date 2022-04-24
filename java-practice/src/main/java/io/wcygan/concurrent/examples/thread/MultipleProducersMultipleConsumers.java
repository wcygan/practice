package io.wcygan.concurrent.examples.thread;

import io.wcygan.concurrent.collections.queue.ArrayBlockingQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * This example shows threads cooperating through a shared {@link ArrayBlockingQueue}.
 *
 * At the end of the simulation, poison is sent into the queue to shut the consumers down.
 */
public class MultipleProducersMultipleConsumers {

    private static final Integer NUM_PRODUCERS = 10;
    private static final Integer NUM_CONSUMERS = 10;
    private static final Integer PACKETS_TO_SEND = 3;
    private static final Packet POISON = new Packet(0, "time to die!!");

    public static void main(String[] args) throws InterruptedException {
        // ArrayBlockingQueue is a limiting factor on the throughput of the application
        ArrayBlockingQueue<Packet> queue = new ArrayBlockingQueue<>(5);

        List<Thread> producers = new ArrayList<>();
        List<Thread> consumers = new ArrayList<>();

        // start producers
        for (int i = 0; i < NUM_PRODUCERS; i++) {
            Thread producer = producer(queue);
            producer.start();
            producers.add(producer);
        }

        // start consumer
        for (int i = 0; i < NUM_CONSUMERS; i++) {
            Thread consumer = consumer(queue);
            consumer.start();
            consumers.add(consumer);
        }

        // wait until finished
        for (var p : producers) {
            p.join();
        }

        queue.add(POISON);

        for (var c : consumers) {
            c.join();
        }

        System.out.println("Done!");
    }

    private static Thread producer(ArrayBlockingQueue<Packet> queue) {
        return new Thread(() -> {
            System.out.println("Starting producer!");

            for (int i = 0; i < PACKETS_TO_SEND; i++) {
                Packet toSend =
                        new Packet(i, "Hello from " + Thread.currentThread().getName());
                queue.add(toSend);
                System.out.println(Thread.currentThread().getName() + " Produced: (" + i + ": " + toSend.data + ")");
            }

            System.out.println("Producer " + Thread.currentThread().getName() + " is shutting down!");
        });
    }

    private static Thread consumer(ArrayBlockingQueue<Packet> queue) {
        return new Thread(() -> {
            System.out.println("Starting consumer!");

            while (true) {
                Packet received = queue.remove();
                if (received == POISON) {
                    queue.add(POISON);
                    break;
                }

                System.out.println(Thread.currentThread().getName() + " Consumed: (" + received.index + ": "
                        + received.data + ")");
            }

            System.out.println("Consumer " + Thread.currentThread().getName() + "  is shutting down!");
        });
    }

    static class Packet {
        int index;
        String data;

        public Packet(int index, String data) {
            this.index = index;
            this.data = data;
        }
    }
}
