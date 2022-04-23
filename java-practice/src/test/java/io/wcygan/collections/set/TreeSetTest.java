package io.wcygan.collections.set;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class TreeSetTest {
    @Test
    public void insertHappyPath() {
        TreeSet<Integer> s = new TreeSet<>();

        assertNull(s.minimum());
        assertNull(s.maximum());
        for (int i = 0; i < 10; i++) {
            assertTrue(s.add(i));
            assertTrue(s.contains(i));
        }
        assertEquals(s.minimum(), 0);
        assertEquals(s.maximum(), 9);
    }

    @Test
    public void doesNotContain() {
        Set<Integer> s = new TreeSet<>();
        for (int i = 0; i < 10; i++) {
            assertFalse(s.contains(i));
        }
    }

    @Test
    public void randomInsertions() {
        Random random = new Random();
        var keys = IntStream.range(0, 50).map(i -> random.nextInt()).boxed().collect(Collectors.toSet());

        var min = keys.stream().min(Integer::compare).get();
        var max = keys.stream().max(Integer::compare).get();

        TreeSet<Integer> s = new TreeSet<>();
        for (var key : keys) {
            assertTrue(s.add(key));
            assertFalse(s.add(key));
            assertTrue(s.contains(key));
        }

        assertEquals(min, s.minimum());
        assertEquals(max, s.maximum());
    }

    @Test
    public void insertAndDeleteSequentially() {
        Set<Integer> s = new TreeSet<>();
        for (int i = 0; i < 10; i++) {
            assertTrue(s.add(i));
            assertTrue(s.contains(i));
            assertTrue(s.remove(i));
            assertFalse(s.contains(i));
        }
    }

    @Test
    public void insertAllThenDeleteAll() {
        Set<Integer> s = new TreeSet<>();
        for (int i = 0; i < 10; i++) {
            assertTrue(s.add(i));
            assertTrue(s.contains(i));
        }

        for (int i = 0; i < 10; i++) {
            assertTrue(s.remove(i));
            assertFalse(s.contains(i));
        }
    }

    @Test
    public void randomInsertAndDeleteSequentially() {
        Random random = new Random();
        var keys = IntStream.range(0, 50).map(i -> random.nextInt()).boxed().collect(Collectors.toSet());

        Set<Integer> s = new TreeSet<>();
        for (var key : keys) {
            assertTrue(s.add(key));
            assertFalse(s.add(key));
            assertTrue(s.contains(key));
            assertTrue(s.remove(key));
            assertFalse(s.contains(key));
        }
    }

    @Test
    public void randomInsertAllThenDeleteAll() {
        Random random = new Random();
        var keys = IntStream.range(0, 50).map(i -> random.nextInt(500)).boxed().collect(Collectors.toSet());

        Set<Integer> s = new TreeSet<>();
        for (var key : keys) {
            assertTrue(s.add(key));
            assertFalse(s.add(key));
            assertTrue(s.contains(key));
        }

        for (var key : keys) {
            assertTrue(s.contains(key));
            assertTrue(s.remove(key));
            assertFalse(s.contains(key));
        }
    }
}
