package io.wcygan.collections.list;

import java.util.Optional;

public class LinkedList<T> implements List<T> {

    int size = 0;
    Node<T> head = null;

    @Override
    public void add(T data) {
        Node<T> toAdd = new Node<>(data);

        if (head == null) {
            this.head = toAdd;
        } else {
            Optional.ofNullable(last()).ifPresent(n -> n.link(toAdd));
        }

        size++;
    }

    @Override
    public T get(int index) {
        Node<T> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T remove(int index) {
        Node<T> prev = head;
        Node<T> curr = head;

        for (int i = 0; i < index; i++) {
            prev = curr;
            curr = curr.next;
        }

        if (curr == head) {
            head = curr.next;
        } else {
            prev.link(curr.next);
        }

        size--;

        return curr.data;
    }

    private Node<T> last() {
        if (head == null) {
            return null;
        }

        Node<T> curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }

        return curr;
    }

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }

        void link(Node<T> other) {
            this.next = other;
        }
    }
}
