package io.wcygan.collections.tree;

/**
 * Left-leaning Red-Black Tree
 * <p>
 * Adapted from https://www.cs.princeton.edu/~rs/talks/LLRB/LLRB.pdf
 *
 * @param <Key>   the key type
 * @param <Value> the value type
 */
public class LLRedBlackTree<Key extends Comparable<Key>, Value> implements SearchTree<Key, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private boolean color;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.color = RED;
        }
    }

    @Override
    public Value search(Key key) {
        throw new Error("Not Implemented");
    }

    @Override
    public Value insert(Key key, Value value) {
        throw new Error("Not Implemented");
    }

    @Override
    public Value remove(Key key) {
        throw new Error("Not Implemented");
    }

    @Override
    public boolean containsKey(Key key) {
        throw new Error("Not Implemented");
    }
}
