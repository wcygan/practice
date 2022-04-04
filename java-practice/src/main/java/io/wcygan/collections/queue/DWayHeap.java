package io.wcygan.collections.queue;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A D-Way Heap
 * <p>
 * Provides more flexibility than {@link BinaryHeap} because this allows
 * the specification of a branching factor (e.g., how many children each node can have).
 *
 * @param <T>
 */
@ThreadSafe
public class DWayHeap<T> implements Queue<T> {

    private static final Integer ROOT = 0;
    private static final Integer DEFAULT_BRANCHING_FACTOR = 4;

    private final int branchingFactor;
    private final Comparator<T> comparator;
    private final ReentrantReadWriteLock.ReadLock readLock;
    private final ReentrantReadWriteLock.WriteLock writeLock;
    private Object[] heap;
    private int nextFreeIndex = 0;
    private int heapSize = 16;

    public DWayHeap(Comparator<T> comparator) {
        this(comparator, DEFAULT_BRANCHING_FACTOR);
    }

    public DWayHeap(Comparator<T> comparator, int branchingFactor) {
        this.heap = new Object[heapSize];
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

            if (nextFreeIndex >= heapSize) {
                growHeap();
            }

            // bind data to the heap
            int position = nextFreeIndex++;
            heap[position] = data;

            // percolate the data up the heap until it's in the right position
            while (position != ROOT && comesBefore(get(position), get(parent(position)))) {
                swap(position, parent(position));
                position = parent(position);
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
            if (heap[ROOT] == null) {
                return null;
            }

            // extract the minimum
            T extracted = get(ROOT);
            nextFreeIndex--;

            // put a leaf node into the root and then push it down to the proper space via heapify
            heap[ROOT] = heap[nextFreeIndex];
            heapify(ROOT);

            return extracted;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public T peek() {
        readLock.lock();
        try {
            return get(ROOT);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        readLock.lock();
        try {
            return nextFreeIndex == ROOT;
        } finally {
            readLock.unlock();
        }
    }

    @SuppressWarnings("unchecked")
    private T get(int i) {
        return (T) heap[i];
    }

    private void heapify(int i) {
        // find the smallest position
        int smallest = i;
        for (int child : children(i)) {
            if (child <= nextFreeIndex && comesBefore(get(child), get(smallest))) {
                smallest = child;
            }
        }

        // if needed, swap the elements at the parent and (smallest) child positions
        if (smallest != i) {
            swap(i, smallest);
            heapify(smallest);
        }
    }

    private void growHeap() {
        // dynamically resize the heap
        heapSize *= 2;
        heap = Arrays.copyOf(heap, heapSize);
    }

    private List<Integer> children(int i) {
        return IntStream.range(1, branchingFactor + 1)
                .map(x -> (branchingFactor * i) + x)
                .boxed()
                .collect(Collectors.toList());
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
        // return true if the first element is smaller than the second (according to the given comparator)
        return comparator.compare(first, second) < 0;
    }
}
