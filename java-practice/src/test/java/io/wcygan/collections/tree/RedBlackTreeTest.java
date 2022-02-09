package io.wcygan.collections.tree;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.wcygan.testutils.IntegerListGenerator;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnitQuickcheck.class)
public class RedBlackTreeTest {
    @Property(trials = 25)
    public void exerciseRedBlackTree(@From(IntegerListGenerator.class) List<Integer> items) {
        var tree = new LLRedBlackTree<Integer, Integer>();
        int count = items.size();

        for (int i = 0; i < count; i++) {
            var k = items.get(i);
            var v = items.get(count - i - 1);

            assertFalse(tree.containsKey(k));
            assertNull(tree.insert(k, v));
            assertTrue(tree.containsKey(k));
            assertEquals(v, tree.search(k));
        }

        for (int i = 0; i < count; i++) {
            var k = items.get(i);
            var v = items.get(count - i - 1);
            assertTrue(tree.containsKey(k));
            var removed = tree.remove(k);
            assertNotNull(removed);
            assertEquals(v, removed);
            assertFalse(tree.containsKey(k));
            assertNull(tree.search(k));
        }
    }
}
