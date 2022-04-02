package io.wcygan.collections.list;

import java.util.Optional;

public interface List<T> extends Iterable<T> {
    int size();

    boolean isEmpty();

    boolean contains(T t);

    boolean add(T t);

    boolean add(int index, T t);

    boolean set(int index, T t);

    Optional<T> remove(T t);

    Optional<T> remove(int index);

    Optional<T> get(int index);

    Optional<Integer> indexOf(T t);
}
