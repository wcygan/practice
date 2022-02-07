package io.wcygan.algorithms.sorting;

import java.util.List;
import java.util.PriorityQueue;

public class HeapSort<T extends Comparable<T>> implements SortingAlgorithm<T> {
    @Override
    public void sort(List<T> lst) {
        /* make heap */
        PriorityQueue<T> queue = new PriorityQueue<>(lst);
        lst.clear();

        /* dequeue all elements from heap */
        while (!queue.isEmpty()) {
            lst.add(queue.remove());
        }
    }
}
