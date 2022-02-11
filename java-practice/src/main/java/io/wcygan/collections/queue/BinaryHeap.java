package io.wcygan.collections.queue;

import java.util.Arrays;
import java.util.Comparator;

public class BinaryHeap<T> implements Queue<T> {

    private final Comparator<T> comparator;
    private Object[] heap;
    private int n = 0;
    private int maxHeapSize = 16;

    public BinaryHeap(Comparator<T> comparator) {
        this.heap = new Object[maxHeapSize];
        this.comparator = comparator;
    }

    @Override
    public boolean add(T data) {
        if (n >= maxHeapSize) {
            growHeap();
        }

        heap[n] = data;
        n += 1;

        int i = n - 1;
        while (i != 0 && comesBefore(get(i), getParent(i))) {
            swap(i, parent(i));
            i = parent(i);
        }

        return true;
    }

    @Override
    public T remove() {
        T maxItem = get(0);
        heap[0] = heap[n - 1];
        n -= 1;
        heapify(0);
        return maxItem;
    }

    @Override
    public T peek() {
        return get(0);
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    private void growHeap() {
        maxHeapSize *= 2;
        heap = Arrays.copyOf(heap, maxHeapSize);
    }

    private void heapify(int i) {
        int left = leftChild(i);
        int right = rightChild(i);

        int smallest = i;

        if (left <= n && comesBefore(get(left), get(smallest))) {
            smallest = left;
        }

        if (right <= n && comesBefore(get(right), get(smallest))) {
            smallest = right;
        }

        if (smallest != i) {
            swap(i, smallest);
            heapify(smallest);
        }
    }

    @SuppressWarnings("unchecked")
    private T get(int i) {
        return (T) heap[i];
    }

    private T getParent(int i) {
        return get(parent(i));
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
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
