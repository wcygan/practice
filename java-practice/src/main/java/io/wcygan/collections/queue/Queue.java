package io.wcygan.collections.queue;

/**
 * A Queue interface
 *
 * @param <T> the type of elements held in this queue
 * @author Will Cygan
 */
public interface Queue<T> {
    /**
     * Inserts the specified element into the queue if it was possible to do so
     *
     * @param data the element to add
     */
    boolean add(T data);

    /**
     * Attempts to remove the head of the queue
     *
     * @return the element that was removed, else {@code null} if the queue was empty
     */
    T remove();

    T peek();

    boolean isEmpty();
}
