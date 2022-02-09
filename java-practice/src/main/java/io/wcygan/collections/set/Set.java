package io.wcygan.collections.set;

public interface Set<T extends Comparable<T>> {
    boolean contains(T data);

    T insert(T data);

    T delete(T data);

    T minimum();

    T maximum();
}
