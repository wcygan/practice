package io.wcygan.collections.set;

public interface Set<T> {
    boolean contains(T data);

    boolean add(T data);

    boolean remove(T data);
}
