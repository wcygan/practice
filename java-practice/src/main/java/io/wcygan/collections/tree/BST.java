package io.wcygan.collections.tree;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

import static io.wcygan.common.Utilities.isLessThan;

public class BST<K extends Comparable<K>, V> implements SearchTree<K, V> {
    Tree root;

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
        return search(key) != null;
    }

    @Override
    public V search(K key) {
        if (key == null) {
            throw new NullPointerException("key is null");
        }

        return get(this.root, key);
    }

    @Override
    public V insert(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException("arguments are null");
        }

        return put(this.root, key, value);
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new NullPointerException("key is null");
        }

        return attemptDelete(null, this.root, key);
    }

    @Override
    public Set<Pair<K, V>> entrySet() {
        Set<Pair<K, V>> s = new HashSet<>();
        Queue<Tree> nodes = new ArrayDeque<>();

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
    }

    @Override
    public K minimum() {
        return min(root);
    }

    @Override
    public K maximum() {
        return max(root);
    }

    private K min(Tree x) {
        if (x.left == null) return x.key;
        else return min(x.left);
    }

    private K max(Tree x) {
        if (x.right == null) return x.key;
        else return max(x.right);
    }

    private int size(Tree root) {
        if (root == null) {
            return 0;
        }

        return 1 + size(root.left) + size(root.right);
    }

    private V put(Tree tree, K key, V value) {
        // if the tree is empty
        if (this.root == null) {
            this.root = new Tree(key, value);
            return null;
        }

        // If we've found the existing key
        if (tree.key.equals(key)) {
            var oldValue = tree.value;
            tree.value = value;
            return oldValue;
        }

        // Is the given key smaller than the key of the current tree root?
        boolean smaller = isLessThan(key, tree.key);

        // if:       Place the new root in the left subtree
        // else if:  Place the new root in the right subtree
        // else:     Recurse down to the next subtree
        if (smaller && tree.left == null) {
            tree.left = new Tree(key, value);
            return null;
        } else if (!smaller && tree.right == null) {
            tree.right = new Tree(key, value);
            return null;
        } else {
            var subtree = smaller ? tree.left : tree.right;
            return put(subtree, key, value);
        }
    }

    private V get(Tree tree, K key) {
        if (tree == null) {
            return null;
        }

        if (tree.key.equals(key)) {
            return tree.value;
        }

        boolean smaller = isLessThan(key, tree.key);
        var subtree = smaller ? tree.left : tree.right;
        return get(subtree, key);
    }

    private V attemptDelete(Tree parent, Tree current, K key) {
        if (current == null) {
            return null;
        }

        if (current.key.equals(key)) {
            return delete(parent, current);
        }

        var next = isLessThan(key, current.key) ? current.left : current.right;
        return attemptDelete(current, next, key);
    }

    private V delete(Tree parent, Tree toDelete) {
        if (toDelete == null) {
            return null;
        }

        var hasLeft = toDelete.left != null;
        var hasRight = toDelete.right != null;

        if (hasLeft && hasRight) {
            return deleteTreeWithTwoChildren(toDelete);
        } else if (hasLeft || hasRight) {
            return deleteTreeWithOneChild(parent, toDelete);
        } else {
            return deleteTreeWithNoChildren(parent, toDelete);
        }
    }

    private V deleteTreeWithTwoChildren(Tree toReplace) {
        var oldValue = toReplace.value;

        // find minimum of right subtree
        var toDelete = toReplace.right;
        var toDeleteParent = toReplace;

        while (toDelete.left != null) {
            toDeleteParent = toDelete;
            toDelete = toDelete.left;
        }

        // replace key and value with minimum of right subtree
        toReplace.key = toDelete.key;
        toReplace.value = toDelete.value;

        // delete minimum of right subtree
        delete(toDeleteParent, toDelete);

        return oldValue;
    }

    private V deleteTreeWithOneChild(Tree parent, Tree toDelete) {
        var child = Optional.ofNullable(toDelete.left).orElse(toDelete.right);

        if (toDelete == this.root) {
            this.root = child;
        } else if (isLessThan(toDelete.key, parent.key)) {
            parent.left = child;
        } else {
            parent.right = child;
        }

        return toDelete.value;
    }

    private V deleteTreeWithNoChildren(Tree parent, Tree toDelete) {
        if (toDelete == this.root) {
            this.root = null;
        } else if (isLessThan(toDelete.key, parent.key)) {
            parent.left = null;
        } else {
            parent.right = null;
        }

        return toDelete.value;
    }

    private class Tree {
        K key;
        V value;
        Tree left, right;

        private Tree(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
