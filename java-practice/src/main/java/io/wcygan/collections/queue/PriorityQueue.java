package io.wcygan.collections.queue;

import java.util.Arrays;
import java.util.Comparator;

public class PriorityQueue<T> implements Queue<T> {

    private final Comparator<T> comparator;
    private Object[] heap;
    private int n = 0;
    private int maxHeapSize = 10;

    public PriorityQueue(Comparator<T> comparator) {
        this.heap = new Object[maxHeapSize];
        this.comparator = comparator;
    }

    @Override
    public void add(T data) {
        if (n >= maxHeapSize) {
            growHeap();
        }

        heap[n] = data;
        n += 1;

        int i = n - 1;

        while (i != 0 && comesBefore(getParent(i), get(i))) {
            T temp = get(i);
            heap[i] = heap[parent(i)];
            heap[parent(i)] = temp;
            i = parent(i);
        }
    }

    @Override
    public T remove() {
        T maxItem = get(0);
        heap[0] = heap[n - 1];
        n -= 1;
        heapify(0);
        return maxItem;
    }

    private void growHeap() {
        maxHeapSize *= 2;
        heap = Arrays.copyOf(heap, maxHeapSize);
    }

    private void heapify(int i) {
        int left = leftChild(i);
        int right = rightChild(i);

        int largest = i;

        if (left <= n && comparator.compare(get(left), get(largest)) > 0) {
            largest = left;
        }

        if (right <= n && comparator.compare(get(right), get(largest)) > 0) {
            largest = right;
        }

        if (largest != i) {
            T temp = get(i);
            heap[i] = heap[largest];
            heap[largest] = temp;
            heapify(largest);
        }
    }

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

    private boolean comesBefore(T first, T second) {
        return comparator.compare(first, second) < 0;
    }
}
