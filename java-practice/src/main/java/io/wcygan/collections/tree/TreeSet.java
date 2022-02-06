package io.wcygan.collections.tree;

import io.wcygan.collections.set.Set;

import java.util.Optional;

import static org.apache.commons.lang3.compare.ComparableUtils.is;


public class TreeSet<T extends Comparable<T>> implements Set<T> {

    Node<T> root;

    public TreeSet() {
        this.root = null;
    }

    @Override
    public boolean contains(T data) {
        return search(root, data);
    }

    public boolean search(Node<T> root, T data) {
        if (Optional.ofNullable(root).isEmpty()) {
            return false;
        }

        if (root.data.equals(data)) {
            return true;
        }

        if (is(data).lessThan(root.data)) {
            return search(root.left, data);
        } else {
            return search(root.right, data);
        }
    }

    @Override
    public boolean insert(T data) {
        Node<T> toAdd = new Node<>(data);

        if (Optional.ofNullable(this.root).isEmpty()) {
            this.root = toAdd;
            return true;
        }

        return insert(null, this.root, toAdd);
    }

    public boolean insert(Node<T> parent, Node<T> current, Node<T> toAdd) {
        if (Optional.ofNullable(current).isEmpty()) {
            if (toAdd.isLessThan(parent)) {
                parent.left = toAdd;
            } else {
                parent.right = toAdd;
            }
            toAdd.parent = parent;
            return true;
        }

        if (current.data.equals(toAdd.data)) {
            return false;
        }

        if (toAdd.isLessThan(current)) {
            return insert(current, current.left, toAdd);
        } else {
            return insert(current, current.right, toAdd);
        }
    }

    @Override
    public boolean delete(T data) {
        throw new Error("not implemented");
    }

    @Override
    public T minimum() {
        throw new Error("not implemented");
    }

    @Override
    public T maximum() {
        throw new Error("not implemented");
    }

    private static class Node<T extends Comparable<T>> {
        T data;
        Node<T> left;
        Node<T> right;
        Node<T> parent;

        public Node(T data) {
            this.data = data;
        }

        public boolean isLessThan(Node<T> other) {
            return isLessThan(other.data);
        }

        public boolean isLessThan(T data) {
            return is(this.data).lessThan(data);
        }
    }
}
