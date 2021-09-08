package io.wcygan.collections.queue;

/**
 * A Queue interface
 *
 * @author Will Cygan
 * @param <T> the type of elements held in this queue
 */
public interface Queue<T> {
  /**
   * Inserts the specified element into the queue if it was possible to do so
   *
   * @param t the element to add
   * @return {@code true} if the element was added, else {@code false}
   */
  boolean add(T t);

  /**
   * Attempts to remove the head of the queue
   *
   * @return the element that was removed, else {@code null} if the queue was empty
   */
  T remove();

  /**
   * Attempts to return the head of the queue
   *
   * @return the element at the head of the queue, else {@code null} if the queue was empty
   */
  T peek();
}
