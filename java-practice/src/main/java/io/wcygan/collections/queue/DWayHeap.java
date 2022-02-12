package io.wcygan.collections.queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 * A D-Way Heap
 * <p>
 * Provides more flexibility than {@link BinaryHeap} because this allows
 * the specification of a branching factor (e.g., how many children each node can have).
 *
 * @param <T>
 */
public class DWayHeap<T> implements Queue<T> {

    private static final Integer DEFAULT_BRANCHING_FACTOR = 4;

    private final int branchingFactor;
    private final Comparator<T> comparator;
    private final ReentrantReadWriteLock.ReadLock readLock;
    private final ReentrantReadWriteLock.WriteLock writeLock;
    private Object[] heap;
    private int n = 0;
    private int maxHeapSize = 16;

    public DWayHeap(Comparator<T> comparator) {
        this(comparator, DEFAULT_BRANCHING_FACTOR);
    }

    public DWayHeap(Comparator<T> comparator, int branchingFactor) {
        this.heap = new Object[maxHeapSize];
        this.branchingFactor = branchingFactor;
        this.comparator = comparator;
        var lock = new ReentrantReadWriteLock();
        this.readLock = lock.readLock();
        this.writeLock = lock.writeLock();
    }

    @Override
    public boolean add(T data) {
        writeLock.lock();
        try {
            if (data == null) {
                return false;
            }

            if (n >= maxHeapSize) {
                growHeap();
            }

            heap[n] = data;
            n += 1;

            int i = n - 1;
            while (i != 0 && comesBefore(get(i), get(parent(i)))) {
                swap(i, parent(i));
                i = parent(i);
            }

            return true;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public T remove() {
        writeLock.lock();
        try {
            if (heap[0] == null) {
                return null;
            }

            T maxItem = get(0);
            heap[0] = heap[n - 1];
            n -= 1;
            heapify(0);
            return maxItem;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public T peek() {
        readLock.lock();
        try {
            return get(0);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        readLock.lock();
        try {
            return n == 0;
        } finally {
            readLock.unlock();
        }
    }

    @SuppressWarnings("unchecked")
    private T get(int i) {
        return (T) heap[i];
    }

    private void heapify(int i) {
        int smallest = i;
        for (int child : children(i)) {
            if (child <= n && comesBefore(get(child), get(smallest))) {
                smallest = child;
            }
        }

        if (smallest != i) {
            swap(i, smallest);
            heapify(smallest);
        }
    }

    private void growHeap() {
        maxHeapSize *= 2;
        heap = Arrays.copyOf(heap, maxHeapSize);
    }

    private List<Integer> children(int i) {
        return IntStream.range(1, branchingFactor + 1)
                .map(x -> (branchingFactor * i) + x)
                .boxed()
                .toList();
    }

    private Integer parent(int i) {
        return (i - 1) / branchingFactor;
    }

    private void swap(int x, int y) {
        T t = get(x);
        heap[x] = get(y);
        heap[y] = t;
    }

    private boolean comesBefore(T first, T second) {
        return comparator.compare(first, second) < 0;
    }
}
