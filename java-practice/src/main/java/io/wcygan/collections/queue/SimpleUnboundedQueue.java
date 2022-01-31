package io.wcygan.collections.queue;

import java.util.LinkedList;

public class SimpleUnboundedQueue<T> implements Queue<T> {

    LinkedList<T> queue;

    public SimpleUnboundedQueue() {
        this.queue = new LinkedList<>();
    }

    @Override
    public void add(T data) {
        queue.add(data);
    }

    @Override
    public T remove() {
        return queue.remove(0);
    }
}
