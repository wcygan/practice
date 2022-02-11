package io.wcygan.collections.list;

public interface List<T> {
    boolean add(T data);

    T get(int index);

    int size();

    T remove(int index);
}
