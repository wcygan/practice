package io.wcygan.collections.tree;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.wcygan.common.Utilities.randomUniqueStrings;
import static org.junit.jupiter.api.Assertions.*;

public class SearchTreeTest {

    private static final String K = "Hello";
    private static final String V = "World";

    private static Stream<Arguments> treeProvider() {
        return Stream.of(Arguments.of(new LLRedBlackTree<>()), Arguments.of(new BST<>()));
    }

    @ParameterizedTest
    @MethodSource("treeProvider")
    public void testSingleNode(SearchTree<String, String> tree) {
        assertFalse(tree.containsKey(K));
        assertNull(tree.insert(K, V));
        assertTrue(tree.containsKey(K));
        assertEquals(V, tree.search(K));
        var es = tree.entrySet();
        assertEquals(1, es.size());
        var fst = es.stream().findFirst();
        assertFalse(fst.isEmpty());
        assertEquals(K, fst.get().getKey());
        assertEquals(V, fst.get().getValue());
        var removed = tree.remove(K);
        assertNotNull(removed);
        assertEquals(V, removed);
        assertFalse(tree.containsKey(K));
    }

    @ParameterizedTest
    @MethodSource("treeProvider")
    public void testManyNodes(SearchTree<String, String> tree) {
        var uniques = randomUniqueStrings(10000);
        int count = uniques.size();

        for (int i = 0; i < count; i++) {
            var k = uniques.get(i);
            var v = uniques.get(count - i - 1);

            assertFalse(tree.containsKey(k));
            assertNull(tree.insert(k, v));
            assertTrue(tree.containsKey(k));
            assertEquals(v, tree.search(k));
        }

        for (int i = 0; i < count; i++) {
            var k = uniques.get(i);
            var v = uniques.get(count - i - 1);
            assertTrue(tree.containsKey(k));
            var removed = tree.remove(k);
            assertNotNull(removed);
            assertEquals(v, removed);
            assertFalse(tree.containsKey(k));
            assertNull(tree.search(k));
        }
    }
}
