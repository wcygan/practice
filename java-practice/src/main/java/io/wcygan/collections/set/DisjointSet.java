package io.wcygan.collections.set;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@ThreadSafe
public class DisjointSet<E> {

    private final Comparator<Node> nodeComparator = Comparator.comparing(o -> o.rank);
    private final Map<E, Node> disjointSets = new HashMap<>();
    private final Lock lock = new ReentrantLock();

    public void union(E first, E second) {
        lock.lock();
        try {
            var firstRoot = find(get(first));
            var secondRoot = find(get(second));

            if (firstRoot != secondRoot) {
                union(firstRoot, secondRoot);
            }
        } finally {
            lock.unlock();
        }
    }

    public E find(E element) {
        lock.lock();
        try {
            var node = find(get(element));

            if (node == node.parent) {
                return node.element;
            }

            node.parent = find(node.parent);
            return node.parent.element;
        } finally {
            lock.unlock();
        }
    }

    private Node find(Node node) {
        if (node == node.parent) {
            return node;
        }

        var sentinel = find(node.parent);
        node.parent = sentinel;
        return sentinel;
    }

    private Node get(E element) {
        var node = disjointSets.get(element);

        if (node == null) {
            node = new Node(element);
            disjointSets.put(element, node);
        }

        return node;
    }

    private void union(Node firstRoot, Node secondRoot) {
        int cmp = firstRoot.compareTo(secondRoot);

        if (cmp < 0) {
            firstRoot.parent = secondRoot;
        } else if (cmp > 0) {
            secondRoot.parent = firstRoot;
        } else {
            secondRoot.parent = firstRoot;
            firstRoot.increaseRank();
        }
    }

    private class Node {
        E element;
        Node parent = this;
        Integer rank = 0;

        public Node(E element) {
            this.element = element;
        }

        public void increaseRank() {
            rank += 1;
        }

        public int compareTo(Node other) {
            return nodeComparator.compare(this, other);
        }
    }
}
