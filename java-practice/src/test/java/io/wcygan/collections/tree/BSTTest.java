package io.wcygan.collections.tree;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

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

    private static Stream<Arguments> heightData() {
        return Stream.of(
                Arguments.of(BST.of(1, 2, 3), 3),
                Arguments.of(BST.of(2, 1, 3), 2),
                Arguments.of(BST.of(1), 1),
                Arguments.of(BST.of(), 0),
                Arguments.of(BST.of(1, 2, 3, 4, 5), 5)
        );
    }

    @ParameterizedTest
    @MethodSource("heightData")
    public void testHeight(BST<Integer, Integer> bst, int expected) {
        assertThat(bst.height()).isEqualTo(expected);
    }
}
