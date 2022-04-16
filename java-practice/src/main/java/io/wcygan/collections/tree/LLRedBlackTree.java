package io.wcygan.collections.tree;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// Adapted from https://www.cs.princeton.edu/~rs/talks/LLRB/LLRB.pdf
public class LLRedBlackTree<K extends Comparable<K>, V> implements SearchTree<K, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private final ReentrantReadWriteLock.ReadLock readLock;
    private final ReentrantReadWriteLock.WriteLock writeLock;
    private Node root;

    public LLRedBlackTree() {
        var lock = new ReentrantReadWriteLock();
        this.readLock = lock.readLock();
        this.writeLock = lock.writeLock();
    }

    @Override
    public V search(K key) {
        readLock.lock();
        try {
            return get(root, key);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public V insert(K key, V value) {
        writeLock.lock();
        try {
            var old = get(key);
            put(key, value);
            return old;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public V remove(K key) {
        writeLock.lock();
        try {
            var old = get(key);
            delete(key);
            return old;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean containsKey(K key) {
        readLock.lock();
        try {
            return contains(key);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public int size() {
        readLock.lock();
        try {
            return size(root);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        readLock.lock();
        try {
            return size() == 0;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Set<Pair<K, V>> entrySet() {
        readLock.lock();
        try {
            Set<Pair<K, V>> s = new HashSet<>();
            Queue<Node> nodes = new ArrayDeque<>();

            if (this.root != null) {
                nodes.add(this.root);
            }

            while (!nodes.isEmpty()) {
                var curr = nodes.remove();
                s.add(Pair.of(curr.key, curr.value));
                Optional.ofNullable(curr.left).ifPresent(nodes::add);
                Optional.ofNullable(curr.right).ifPresent(nodes::add);
            }

            return s;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public K minimum() {
        readLock.lock();
        try {
            return min(root);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public K maximum() {
        readLock.lock();
        try {
            return max(root);
        } finally {
            readLock.unlock();
        }
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return 1 + size(x.left) + size(x.right);
    }

    private boolean contains(K key) {
        return (get(key) != null);
    }

    private V get(K key) {
        return get(root, key);
    }

    private V get(Node x, K key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x.value;
            else if (cmp < 0) x = x.left;
            else x = x.right;
        }
        return null;
    }

    private K min(Node x) {
        if (x == null) return null;
        if (x.left == null) return x.key;
        else return min(x.left);
    }

    private K max(Node x) {
        if (x == null) return null;
        if (x.right == null) return x.key;
        else return max(x.right);
    }

    private void put(K key, V value) {
        root = insert(root, key, value);
        root.color = BLACK;
    }

    private Node insert(Node h, K key, V value) {
        if (h == null) return new Node(key, value);

        if (eq(key, h.key)) h.value = value;
        else if (less(key, h.key)) h.left = insert(h.left, key, value);
        else h.right = insert(h.right, key, value);

        if (isRed(h.right)) h = rotateLeft(h);

        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);

        if (isRed(h.left) && isRed(h.right)) colorFlip(h);

        return h;
    }

    private Node deleteMin(Node h) {
        if (h.left == null) return null;

        if (!isRed(h.left) && !isRed(h.left.left)) h = moveRedLeft(h);

        h.left = deleteMin(h.left);

        return fixUp(h);
    }

    public void delete(K key) {
        root = delete(root, key);
        if (root != null) {
            root.color = BLACK;
        }
    }

    private Node delete(Node h, K key) {
        if (less(key, h.key)) {
            if (!isRed(h.left) && !isRed(h.left.left)) h = moveRedLeft(h);
            h.left = delete(h.left, key);
        } else {
            if (isRed(h.left)) {
                h = rotateRight(h);
            }

            if (eq(key, h.key) && (h.right == null)) {
                return null;
            }

            if (!isRed(h.right) && !isRed(h.right.left)) {
                // offender
                h = moveRedRight(h);
            }

            if (eq(key, h.key)) {
                h.value = get(h.right, min(h.right));
                h.key = min(h.right);
                h.right = deleteMin(h.right);
            } else h.right = delete(h.right, key);
        }

        return fixUp(h);
    }

    private boolean less(K a, K b) {
        return a.compareTo(b) < 0;
    }

    private boolean eq(K a, K b) {
        return a.compareTo(b) == 0;
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return (x.color == RED);
    }

    private void colorFlip(Node h) {
        h.color = !h.color;
        h.right.color = !h.right.color;
        h.left.color = !h.left.color;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        return x;
    }

    private Node moveRedLeft(Node h) {
        colorFlip(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            colorFlip(h);
        }
        return h;
    }

    private Node moveRedRight(Node h) {
        colorFlip(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            colorFlip(h);
        }
        return h;
    }

    private Node fixUp(Node h) {
        if (isRed(h.right)) h = rotateLeft(h);

        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);

        if (isRed(h.left) && isRed(h.right)) colorFlip(h);

        return h;
    }

    public class Node {
        K key;
        V value;
        Node left, right;
        boolean color;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.color = RED;
        }
    }
}