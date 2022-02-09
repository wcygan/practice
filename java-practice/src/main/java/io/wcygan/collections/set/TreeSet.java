package io.wcygan.collections.set;

import io.wcygan.collections.tree.LLRedBlackTree;
import io.wcygan.collections.tree.SearchTree;

public class TreeSet<T extends Comparable<T>> implements Set<T> {

    SearchTree<T, T> tree = new LLRedBlackTree<>();
    ;

    @Override
    public boolean contains(T data) {
        return tree.containsKey(data);
    }

    @Override
    public T insert(T data) {
        return tree.insert(data, data);
    }

    @Override
    public T delete(T data) {
        return tree.remove(data);
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
