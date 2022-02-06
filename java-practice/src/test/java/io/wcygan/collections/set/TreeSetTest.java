package io.wcygan.collections.set;

import io.wcygan.collections.tree.TreeSet;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TreeSetTest {
    @Test
    public void insertHappyPath() {
        Set<Integer> s = new TreeSet<>();
        for (int i = 0; i < 10; i++) {
            assertTrue(s.insert(i));
            assertTrue(s.contains(i));
        }
    }

    @Test
    public void doesNotContain() {
        Set<Integer> s = new TreeSet<>();
        for (int i = 0; i < 10; i++) {
            assertFalse(s.contains(i));
        }
    }

    @Test
    public void doubleInsert() {
        Set<Integer> s = new TreeSet<>();
        for (int i = 0; i < 10; i++) {
            assertTrue(s.insert(i));
            assertFalse(s.insert(i));
            assertTrue(s.contains(i));
        }
    }

    @Test
    public void randomInsertions() {
        Random random = new Random();
        var keys = IntStream.range(0, 25).map(i -> random.nextInt()).boxed().collect(Collectors.toList());
        
        Set<Integer> s = new TreeSet<>();
        for (var key : keys) {
            assertTrue(s.insert(key));
            assertFalse(s.insert(key));
            assertTrue(s.contains(key));
        }
    }
}
