package io.wcygan.concurrent.collections.stack;

import io.wcygan.collections.stack.Stack;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class NonblockingStack<T> implements Stack<T> {

    private static final Integer FIRST_RANK = 1;

    AtomicReference<Node<T>> top = new AtomicReference<>();

    @Override
    public void push(T data) {
        Node<T> newHead = new Node<T>(data, -1);
        Node<T> oldHead;
        do {
            oldHead = top.get();
            newHead.next = oldHead;
            newHead.rank = (oldHead == null) ? FIRST_RANK : oldHead.rank + 1;
        } while (!top.compareAndSet(oldHead, newHead));
    }

    @Override
    public T pop() {
        Node<T> oldHead;
        Node<T> newHead;
        do {
            oldHead = top.get();
            if (oldHead == null) return null;
            newHead = oldHead.next;
        } while (!top.compareAndSet(oldHead, newHead));
        return oldHead.item;
    }

    @Override
    public T peek() {
        return Optional.ofNullable(top.get()).map(node -> node.item).orElse(null);
    }

    @Override
    public int size() {
        return Optional.ofNullable(top.get()).map(node -> node.rank).orElse(0);
    }

    private static class Node<T> {
        public final T item;
        public int rank;
        public Node<T> next;

        public Node(T item, int rank) {
            this.item = item;
            this.rank = rank;
        }
    }
}
