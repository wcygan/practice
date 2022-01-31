package io.wcygan.collections.stack;

import java.util.LinkedList;
import java.util.List;

public class SimpleStack<T> implements Stack<T> {

    List<T> list;

    public SimpleStack() {
        list = new LinkedList<>();
    }

    public void push(T data) {
        list.add(0, data);
    }

    public T pop() {
        return list.remove(0);
    }

    public T peek() {
        return list.get(0);
    }

    public int size() {
        return list.size();
    }
}
