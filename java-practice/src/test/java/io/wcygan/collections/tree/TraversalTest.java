package io.wcygan.collections.tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TraversalTest {

    private static final Object PRESENT = new Object();
    private static BST<Integer, Object> tree;

    /**
     * <pre>
     *  This is the tree structure of the test...
     *
     *           4
     *         /   \
     *       2      6
     *     /  \    /  \
     *    1   3   5    7
     *
     * </pre>
     */
    @BeforeAll
    static void setup() {
        tree = new BST<>();
        List.of(4, 2, 6, 1, 3, 5, 7).forEach(x -> tree.insert(x, PRESENT));
    }

    @Test
    public void inOrder() {
        Assertions.assertNotNull(tree);
        var lst = new ArrayList<Integer>();
        inOrder(lst, tree.root());
        Assertions.assertEquals(List.of(1, 2, 3, 4, 5, 6, 7), lst);
    }

    @Test
    public void preOrder() {
        Assertions.assertNotNull(tree);
        var lst = new ArrayList<Integer>();
        preOrder(lst, tree.root());
        Assertions.assertEquals(List.of(4, 2, 1, 3, 6, 5, 7), lst);
    }

    @Test
    public void postOrder() {
        Assertions.assertNotNull(tree);
        var lst = new ArrayList<Integer>();
        postOrder(lst, tree.root());
        Assertions.assertEquals(List.of(1, 3, 2, 5, 7, 6, 4), lst);
    }

    private void preOrder(List<Integer> results, BST<Integer, Object>.Node node) {
        if (node == null) {
            return;
        }

        results.add(node.key);
        preOrder(results, node.left);
        preOrder(results, node.right);
    }

    private void postOrder(List<Integer> results, BST<Integer, Object>.Node node) {
        if (node == null) {
            return;
        }

        postOrder(results, node.left);
        postOrder(results, node.right);
        results.add(node.key);
    }

    private void inOrder(List<Integer> results, BST<Integer, Object>.Node node) {
        if (node == null) {
            return;
        }

        inOrder(results, node.left);
        results.add(node.key);
        inOrder(results, node.right);
    }
}
