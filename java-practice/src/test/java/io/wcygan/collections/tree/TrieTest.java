package io.wcygan.collections.tree;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.wcygan.common.Utilities;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(JUnitQuickcheck.class)
public class TrieTest {
    @Test
    public void happyPath() {
        var h = "Hello";
        var w = "World";
        var hw = h + " " + w;
        var trie = new Trie();
        assertFalse(trie.search(hw).isPresent());
        assertTrue(trie.add(hw));
        assertFalse(trie.search(h).isPresent());
        assertTrue(trie.search(hw).isPresent());
        assertTrue(trie.add(h));
        assertTrue(trie.search(h).isPresent());
        assertTrue(trie.remove(hw));
        assertFalse(trie.search(hw).isPresent());
    }

    @Property(trials = 25)
    public void exerciseTrie(int size) {
        int limit = 500;
        var strings = Utilities.randomUniqueStrings(size % limit);
        var trie = new Trie();

        // add all
        for (var s : strings) {
            assertTrue(trie.add(s));
            assertTrue(trie.search(s).isPresent());
        }

        // remove all
        for (var s : strings) {
            assertTrue(trie.remove(s));
            assertFalse(trie.search(s).isPresent());
        }
    }
}
