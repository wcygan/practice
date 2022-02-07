package io.wcygan.collections.dictionary;

import java.util.Collection;
import java.util.Set;

import static io.wcygan.common.Utilities.isLessThan;

public class TreeMap<K extends Comparable<K>, V> implements Map<K, V> {
    Tree<K, V> root;

    @Override
    public int size() {
        return size(this.root);
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean containsKey(K key) {
        throw new Error("Not Implemented");
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new NullPointerException("key is null");
        }

        return get(this.root, key);
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException("arguments are null");
        }

        return put(this.root, key, value);
    }

    @Override
    public V remove(K key) {
        throw new Error("Not Implemented");
    }

    @Override
    public void clear() {
        throw new Error("Not Implemented");
    }

    @Override
    public Set<K> keySet() {
        throw new Error("Not Implemented");
    }

    @Override
    public Collection<V> values() {
        throw new Error("Not Implemented");
    }

    private int size(Tree<K, V> root) {
        if (root == null) {
            return 0;
        }

        return 1 + size(root.left) + size(root.right);
    }

    private V put(Tree<K, V> tree, K key, V value) {
        if (this.root == null) {
            // The tree is empty
            this.root = Tree.root(key, value);
            return null;
        }

        if (tree.key.equals(key)) {
            // We've found the existing key
            var oldValue = tree.value;
            tree.value = value;
            return oldValue;
        }

        boolean smaller = isLessThan(tree.key, key);
        if (smaller && tree.left == null) {
            // Place the new root in the left subtree
            tree.left = Tree.root(key, value);
            return null;
        } else if (!smaller && tree.right == null) {
            // Place the new root in the right subtree
            tree.right = Tree.root(key, value);
            return null;
        } else {
            // Recurse down to the next subtree
            var subtree = smaller ? tree.left : tree.right;
            return put(subtree, key, value);
        }
    }

    private V get(Tree<K, V> tree, K key) {
        if (tree == null) {
            return null;
        }

        if (tree.key.equals(key)) {
            return tree.value;
        }

        boolean smaller = isLessThan(tree.key, key);
        var subtree = smaller ? tree.left : tree.right;
        return get(subtree, key);
    }

    private static class Tree<K extends Comparable<K>, V> {
        K key;
        V value;
        Tree<K, V> left;
        Tree<K, V> right;

        private Tree(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public static <K extends Comparable<K>, V> Tree<K, V> root(K key, V value) {
            return new Tree<>(key, value);
        }
    }
}
