package io.wcygan.collections.queue;

import java.util.LinkedList;

public class SimpleUnboundedQueue<T> implements Queue<T> {

  LinkedList<T> queue;

  public SimpleUnboundedQueue() {
    this.queue = new LinkedList<>();
  }

  @Override
  public boolean add(T t) {
    return queue.add(t);
  }

  @Override
  public T remove() {
    return queue.remove(0);
  }

  @Override
  public T peek() {
    return queue.get(0);
  }
}
