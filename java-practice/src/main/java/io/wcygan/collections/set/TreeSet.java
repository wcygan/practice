package io.wcygan.collections.set;

import io.wcygan.collections.tree.LLRedBlackTree;
import io.wcygan.collections.tree.SearchTree;

public class TreeSet<T extends Comparable<T>> implements Set<T> {

    private static final Object PRESENT = new Object();
    private final SearchTree<T, Object> tree = new LLRedBlackTree<>();

    @Override
    public boolean contains(T data) {
        return tree.containsKey(data);
    }

    @Override
    public boolean insert(T data) {
        if (tree.containsKey(data)) {
            return false;
        } else {
            tree.insert(data, PRESENT);
            return true;
        }
    }

    @Override
    public boolean delete(T data) {
        return tree.remove(data) != null;
    }

    @Override
    public T minimum() {
        return tree.smallestKey();
    }

    @Override
    public T maximum() {
        return tree.largestKey();
    }
}
