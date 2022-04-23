package io.wcygan.concurrent.collections.queue;

import io.wcygan.collections.queue.Queue;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@ThreadSafe
public class ArrayBlockingQueue<T> implements Queue<T> {

    private final ReentrantLock lock;
    private final Condition notEmpty;
    private final Condition notFull;
    Object[] arr;
    int size;
    int addIndex;
    int removeIndex;

    public ArrayBlockingQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        size = 0;
        addIndex = 0;
        removeIndex = 0;
        this.arr = new Object[capacity];
        this.lock = new ReentrantLock();
        this.notEmpty = lock.newCondition();
        this.notFull = lock.newCondition();
    }

    @Override
    public boolean add(T data) {
        lock.lock();
        try {
            while (size == arr.length) {
                try {
                    notFull.await();
                } catch (InterruptedException ignored) {
                    // ignored
                }
            }

            arr[addIndex] = data;
            size++;

            addIndex++;
            if (addIndex == arr.length) {
                addIndex = 0;
            }

            notEmpty.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T remove() {
        lock.lock();
        try {
            while (size == 0) {
                try {
                    notEmpty.await();
                } catch (InterruptedException ignored) {
                    // ignored
                }
            }

            T data = itemAt(removeIndex);
            nullify(removeIndex);
            size--;

            removeIndex++;
            if (removeIndex == arr.length) {
                removeIndex = 0;
            }

            notFull.signal();
            return data;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T peek() {
        lock.lock();
        try {
            return itemAt(removeIndex);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        lock.lock();
        try {
            return size == 0;
        } finally {
            lock.unlock();
        }
    }

    @SuppressWarnings("unchecked")
    private T itemAt(int index) {
        return (T) arr[index];
    }

    private void nullify(int index) {
        arr[index] = null;
    }
}
