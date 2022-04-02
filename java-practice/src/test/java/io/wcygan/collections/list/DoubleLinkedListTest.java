package io.wcygan.collections.list;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleLinkedListTest {
    @Test
    public void valueIterator() {
        List<Integer> values = DoublyLinkedList.of(1, 2, 3);
        Iterator<Integer> iterator = values.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void nodeIterator() {
        DoublyLinkedList<Integer> values = DoublyLinkedList.of(1, 2, 3);
        Iterator<DoublyLinkedList<Integer>.Node> iterator = values.nodes();
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next().value);
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next().value);
        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next().value);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void get() {
        DoublyLinkedList<Integer> values = DoublyLinkedList.of(1, 2, 3);
        Optional<Integer> got;

        got = values.get(0);
        assertTrue(got.isPresent());
        assertEquals(1, got.get());

        got = values.get(1);
        assertTrue(got.isPresent());
        assertEquals(2, got.get());

        got = values.get(2);
        assertTrue(got.isPresent());
        assertEquals(3, got.get());
    }

    @Test
    public void set() {
        DoublyLinkedList<Integer> values = DoublyLinkedList.of(1, 2, 3);
        Optional<Integer> got;

        assertTrue(values.set(0, 10));
        got = values.get(0);
        assertTrue(got.isPresent());
        assertEquals(10, got.get());

        assertTrue(values.set(2, 40));
        got = values.get(2);
        assertTrue(got.isPresent());
        assertEquals(40, got.get());
    }

    @Test
    public void removeByIndex() {
        DoublyLinkedList<String> values = DoublyLinkedList.of("a", "b", "c");
        assertEquals(3, values.size());
        Optional<String> got;

        got = values.remove(1);
        assertTrue(got.isPresent());
        assertEquals("b", got.get());

        got = values.get(1);
        assertTrue(got.isPresent());
        assertEquals("c", got.get());
        assertEquals(2, values.size());
    }

    @Test
    public void removeByValue() {
        DoublyLinkedList<String> values = DoublyLinkedList.of("a", "b", "c");
        assertEquals(3, values.size());

        Optional<String> got;
        got = values.remove("b");
        assertTrue(got.isPresent());
        assertEquals("b", got.get());

        got = values.get(1);
        assertTrue(got.isPresent());
        assertEquals("c", got.get());

        assertEquals(2, values.size());
    }
}
