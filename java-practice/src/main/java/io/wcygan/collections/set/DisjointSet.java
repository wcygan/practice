package io.wcygan.collections.set;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class DisjointSet<E> {

    public final Comparator<Node> nodeComparator = Comparator.comparing(o -> o.rank);
    private final Map<E, Node> disjointSets = new HashMap<>();

    public void union(E first, E second) {
        var firstRoot = find(get(first));
        var secondRoot = find(get(second));

        if (firstRoot != secondRoot) {
            union(firstRoot, secondRoot);
        }
    }

    public E find(E element) {
        var node = find(get(element));

        if (node == node.parent) {
            return node.element;
        }

        node.parent = find(node.parent);

        return node.parent.element;
    }

    private Node find(Node node) {
        while (node != node.parent) {
            node = node.parent;
        }

        return node;
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
