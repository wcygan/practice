package io.wcygan.collections.queue;


import io.wcygan.collections.list.LinkedList;

public class SimpleUnboundedQueue<T> implements Queue<T> {

    LinkedList<T> queue;

    public SimpleUnboundedQueue() {
        this.queue = new LinkedList<>();
    }

    @Override
    public boolean add(T data) {
        return queue.add(data);
    }

    @Override
    public T remove() {
        return queue.remove(0);
    }

    @Override
    public T peek() {
        return queue.get(0);
    }

    @Override
    public boolean isEmpty() {
        return peek() == null;
    }
}
