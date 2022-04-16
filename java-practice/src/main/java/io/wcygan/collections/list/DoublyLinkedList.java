package io.wcygan.collections.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * TODO: fix any {@link UnsupportedOperationException}
 */
public class DoublyLinkedList<T> implements List<T> {
    int size = 0;
    Node head;
    Node tail;

    @SafeVarargs
    public static <T> DoublyLinkedList<T> of(T... t) {
        DoublyLinkedList<T> list = new DoublyLinkedList<>();
        for (T value : t) {
            list.add(value);
        }
        return list;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(T t) {
        Iterator<Node> nodes = nodes();

        for (int i = 0; i < size; i++) {
            if (!nodes.hasNext()) {
                return false;
            }

            if (nodes.next().equals(t)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean add(T t) {
        Node node = new Node(t);
        if (head == null) {
            // empty list
            head = node;
            tail = node;
        } else if (head == tail) {
            // singleton list
            tail = node;
            tail.prev = head;
            head.next = tail;
        } else {
            // multi-element list
            tail.next = node;
            node.prev = tail;
            tail = node;
        }

        size++;
        return true;
    }

    @Override
    public boolean add(int index, T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean set(int index, T t) {
        Iterator<Node> nodes = nodes();

        for (int i = 0; i < index; i++) {
            if (nodes.next() == null) {
                return false;
            }
        }

        if (!nodes.hasNext()) {
            return false;
        }

        nodes.next().value = t;

        return true;
    }

    @Override
    public Optional<T> remove(T t) {
        Iterator<Node> nodes = nodes();

        Node node = null;
        for (int i = 0; i < size(); i++) {
            if (!nodes.hasNext()) {
                return Optional.empty();
            }

            node = nodes.next();
            if (node.value.equals(t)) {
                break;
            }
        }

        if (node != null) {
            return remove(node);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<T> remove(int index) {
        Iterator<Node> nodes = nodes();

        Node node = null;
        for (int i = 0; i <= index; i++) {
            if (!nodes.hasNext()) {
                return Optional.empty();
            }

            node = nodes.next();
        }

        if (node != null) {
            return remove(node);
        } else {
            return Optional.empty();
        }
    }

    private Optional<T> remove(Node node) {
        size--;
        if (head == null && tail == null) {
            // empty list
            return Optional.empty();
        } else if (node == head && node == tail) {
            // singleton list
            head = null;
            tail = null;
            return Optional.of(node.value);
        } else if (node == head) {
            head = head.next;
            head.prev = null;
            return Optional.of(node.value);
        } else if (node == tail) {
            tail = tail.prev;
            tail.next = null;
            return Optional.of(node.value);
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            return Optional.of(node.value);
        }
    }

    @Override
    public Optional<T> get(int index) {
        Iterator<Node> nodes = nodes();

        Node node = null;
        for (int i = 0; i <= index; i++) {
            if (!nodes.hasNext()) {
                return Optional.empty();
            }

            node = nodes.next();
        }

        return Optional.ofNullable(node).map(n -> n.value);
    }

    @Override
    public Optional<Integer> indexOf(T t) {
        Iterator<Node> nodes = nodes();

        for (int i = 0; i < size; i++) {
            if (!nodes.hasNext()) {
                throw new NoSuchElementException();
            }

            if (nodes.next().equals(t)) {
                return Optional.of(i);
            }
        }

        return Optional.empty();
    }

    public Iterator<Node> nodes() {
        return new LinkedListNodeIterator(head);
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator(head);
    }

    public class Node {
        T value;
        Node next;
        Node prev;

        public Node(T value) {
            this.value = value;
        }
    }

    private class LinkedListIterator implements Iterator<T> {
        Node curr;

        public LinkedListIterator(Node curr) {
            this.curr = curr;
        }

        @Override
        public boolean hasNext() {
            return this.curr != null;
        }

        @Override
        public T next() {
            if (curr == null) {
                throw new NoSuchElementException();
            }
            try {
                return curr.value;
            } finally {
                curr = curr.next;
            }
        }
    }

    private class LinkedListNodeIterator implements Iterator<Node> {
        Node curr;

        public LinkedListNodeIterator(Node curr) {
            this.curr = curr;
        }

        @Override
        public boolean hasNext() {
            return this.curr != null;
        }

        @Override
        public Node next() {
            if (curr == null) {
                throw new NoSuchElementException();
            }
            try {
                return curr;
            } finally {
                curr = curr.next;
            }
        }
    }
}
