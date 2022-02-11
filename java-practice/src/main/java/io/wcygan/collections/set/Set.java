package io.wcygan.collections.set;

public interface Set<T extends Comparable<T>> {
    boolean contains(T data);

    boolean insert(T data);

    boolean delete(T data);

    T minimum();

    T maximum();
}
