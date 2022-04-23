package io.wcygan.concurrent.collections.queue;

import io.wcygan.collections.queue.Queue;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Implementation from (Michael and Scott, 1996)
 *
 * <p>https://www.cs.rochester.edu/~scott/papers/1996_PODC_queues.pdf
 *
 * <p>https://ycpcs.github.io/cs365-spring2019/lectures/lecture14.html
 *
 * @param <T> the type of the elements in the queue
 */
@ThreadSafe
public class NonblockingQueue<T> implements Queue<T> {

    private final AtomicReference<Node<T>> head;
    private final AtomicReference<Node<T>> tail;

    public NonblockingQueue() {
        Node<T> node = new Node<T>();
        head = new AtomicReference<>();
        tail = new AtomicReference<>();
        head.set(node);
        tail.set(node);
    }

    @Override
    public boolean add(T data) {
        Node<T> node = new Node<T>();
        node.data = data;
        node.next.set(null);
        Node<T> tail;

        while (true) {
            tail = this.tail.get();
            Node<T> next = tail.next.get();
            if (tail == this.tail.get()) {
                if (next == null) {
                    if (tail.next.compareAndSet(null, node)) {
                        break;
                    }
                } else {
                    this.tail.compareAndSet(tail, next);
                }
            }
        }
        return this.tail.compareAndSet(tail, node);
    }

    @Override
    public T remove() {
        while (true) {
            Node<T> head = this.head.get();
            Node<T> tail = this.tail.get();
            Node<T> next = head.next.get();
            if (head == this.head.get()) {
                if (head == tail) {
                    if (next == null) {
                        return null;
                    }
                    this.tail.compareAndSet(tail, next);
                } else {
                    T value = next.data;
                    if (this.head.compareAndSet(head, next)) {
                        next.data = null;
                        return value;
                    }
                }
            }
        }
    }

    @Override
    public T peek() {
        return head.get().data;
    }

    @Override
    public boolean isEmpty() {
        return head.get() == null;
    }

    static class Node<T> {
        T data;
        AtomicReference<Node<T>> next = new AtomicReference<>();
    }
}
