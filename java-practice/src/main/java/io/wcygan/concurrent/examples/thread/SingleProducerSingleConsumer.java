package io.wcygan.concurrent.examples.thread;

import io.wcygan.concurrent.collections.queue.ArrayBlockingQueue;

/**
 * This example shows two threads cooperating through a shared {@link ArrayBlockingQueue}.
 *
 * At the end of the simulation, {@link POISON} is sent into the queue to shut the consumer down.
 */
public class SingleProducerSingleConsumer {

    private static final Integer PACKETS_TO_SEND = 50;
    private static final Packet POISON = new Packet(0, null);

    public static void main(String[] args) throws InterruptedException {
        // ArrayBlockingQueue is a limiting factor on the throughput of the application
        ArrayBlockingQueue<Packet> queue = new ArrayBlockingQueue<>(5);

        // start producer
        Thread producer = producer(queue);
        producer.start();

        // start consumer
        Thread consumer = consumer(queue);
        consumer.start();

        // wait until finished
        producer.join();
        consumer.join();
    }

    private static Thread producer(ArrayBlockingQueue<Packet> queue) {
        return new Thread(() -> {
            System.out.println("Starting producer!");

            for (int i = 0; i < PACKETS_TO_SEND; i++) {
                Packet toSend = new Packet(i, "Hello!");
                queue.add(toSend);
                System.out.println("Produced: (" + i + ": " + toSend.data + ")");
            }

            System.out.println("Producer is shutting down!");
            queue.add(POISON);
        });
    }

    private static Thread consumer(ArrayBlockingQueue<Packet> queue) {
        return new Thread(() -> {
            System.out.println("Starting consumer!");

            while (true) {
                Packet received = queue.remove();
                if (received == POISON) {
                    break;
                }

                System.out.println("Consumed: (" + received.index + ": " + received.data + ")");
            }

            System.out.println("Consumer is shutting down!");
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
