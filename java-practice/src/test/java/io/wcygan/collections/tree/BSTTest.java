package io.wcygan.collections.tree;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BSTTest {
    @Test
    public void ofKeys() {
        BST<Integer, Integer> bst = BST.of(1, 2, 3);
        assertNotNull(bst.root());
        assertEquals(1, bst.root().key());
        assertEquals(2, bst.root().right().key());
        assertEquals(3, bst.root().right().right().key());
        assertNull(bst.root().left());
    }

    @Test
    public void ofPairs() {
        BST<Integer, String> bst = BST.of(Pair.of(1, "Hello"), Pair.of(2, "Goodbye"), Pair.of(3, "World"));

        assertNotNull(bst.root());
        assertNull(bst.root().left());
        assertNotNull(bst.root().right());
        assertNotNull(bst.root().right().right());
        assertEquals("Hello", bst.root().value());
        assertEquals("Goodbye", bst.root().right().value());
        assertEquals("World", bst.root().right().right().value());
    }
}
