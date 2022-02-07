package io.wcygan.collections.set;

import io.wcygan.common.Utilities;

import java.util.Optional;

import static io.wcygan.common.Utilities.isLessThan;

public class TreeSet<T extends Comparable<T>> implements Set<T> {

    Node<T> root;

    public TreeSet() {
        this.root = null;
    }

    @Override
    public boolean contains(T data) {
        return search(root, data);
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

    @Override
    public boolean delete(T data) {
        return delete(null, this.root, data);
    }

    @Override
    public T minimum() {
        if (root == null) {
            return null;
        }

        var curr = root;

        while (Optional.ofNullable(curr.left).isPresent()) {
            curr = curr.left;
        }

        return curr.data;
    }

    @Override
    public T maximum() {
        if (root == null) {
            return null;
        }

        var curr = root;

        while (Optional.ofNullable(curr.right).isPresent()) {
            curr = curr.right;
        }

        return curr.data;
    }

    private boolean search(Node<T> root, T data) {
        if (Optional.ofNullable(root).isEmpty()) {
            return false;
        }

        if (root.data.equals(data)) {
            return true;
        }

        Node<T> next = isLessThan(data, root.data) ? root.left : root.right;
        return search(next, data);
    }

    private boolean insert(Node<T> parent, Node<T> current, Node<T> toAdd) {
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

        Node<T> next = toAdd.isLessThan(current) ? current.left : current.right;
        return insert(current, next, toAdd);
    }


    private boolean delete(Node<T> parent, Node<T> current, T data) {
        if (Optional.ofNullable(current).isEmpty()) {
            return false;
        }

        if (current.data.equals(data)) {
            return deleteNode(parent, current);
        }

        Node<T> next = isLessThan(data, current.data) ? current.left : current.right;
        return delete(current, next, data);
    }

    private boolean deleteNode(Node<T> parent, Node<T> toDelete) {
        int children = 0;
        var left = Optional.ofNullable(toDelete.left);
        var right = Optional.ofNullable(toDelete.right);
        if (left.isPresent()) {
            children++;
        }

        if (right.isPresent()) {
            children++;
        }

        switch (children) {
            case 0 -> {
                return deleteNodeNoChildren(parent, toDelete);
            }
            case 1 -> {
                return deleteNodeOneChild(parent, toDelete);
            }
            case 2 -> {
                return deleteNodeTwoChildren(toDelete);
            }
            default -> {
                throw new Error("unreachable");
            }
        }
    }

    private boolean deleteNodeNoChildren(Node<T> parent, Node<T> toDelete) {
        if (toDelete == this.root) {
            this.root = null;
            return true;
        }

        if (toDelete.isLessThan(parent)) {
            parent.left = null;
        } else {
            parent.right = null;
        }

        return true;
    }

    private boolean deleteNodeOneChild(Node<T> parent, Node<T> toDelete) {
        var child = Optional.ofNullable(toDelete.left).orElse(toDelete.right);

        if (toDelete == this.root) {
            this.root = child;

        } else {
            if (toDelete.isLessThan(parent)) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }

        child.parent = parent;
        return true;
    }

    private boolean deleteNodeTwoChildren(Node<T> toDelete) {
        var replacerNode = smallestNodeInRightSubtree(toDelete.right);

        if (Optional.ofNullable(replacerNode).isEmpty()) {
            return false;
        }

        toDelete.data = replacerNode.data;

        return deleteNode(replacerNode.parent, replacerNode);
    }

    private Node<T> smallestNodeInRightSubtree(Node<T> root) {
        if (Optional.ofNullable(root).isEmpty()) {
            return null;
        }

        var curr = root;
        while (Optional.ofNullable(curr.left).isPresent()) {
            curr = curr.left;
        }

        return curr;
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
            return Utilities.isLessThan(this.data, data);
        }

    }
}
