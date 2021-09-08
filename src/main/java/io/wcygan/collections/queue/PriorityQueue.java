package io.wcygan.collections.queue;

import java.util.Comparator;
import java.util.Vector;

/**
 * An implementation of a Priority Queue Adapted from CLRS Chapter 6 (Heap Sort)
 *
 * <p>TODO: Implement this and write tests for it
 *
 * @author Will Cygan
 * @param <T> the type of elements held in this queue
 */
public class PriorityQueue<T> implements Queue<T> {

  Vector<T> heap;
  Comparator<T> comparator;

  private PriorityQueue() {}

  public PriorityQueue(Comparator<T> comparator) {
    this(comparator, 16);
    throw new RuntimeException("Not Implemented");
  }

  public PriorityQueue(Comparator<T> comparator, int initialSize) {
    this.comparator = comparator;
    this.heap = new Vector<>(initialSize, initialSize);
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public boolean add(T t) {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public T remove() {
    throw new RuntimeException("Not Implemented");
  }

  @Override
  public T peek() {
    throw new RuntimeException("Not Implemented");
  }

  private void heapify() {
    throw new RuntimeException("Not Implemented");
  }

  /*
   * parent = i / 2
   */
  protected static int parent(int i) {
    return i >> 1;
  }

  /*
   * left child = (i * 2)
   */
  protected static int leftChild(int i) {
    return i << 1;
  }

  /*
   * left child = (i * 2) + 1
   */
  protected static int rightChild(int i) {
    return (i << 1) | 0x1;
  }
}
