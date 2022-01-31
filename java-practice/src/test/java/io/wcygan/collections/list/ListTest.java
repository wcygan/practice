package io.wcygan.collections.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListTest {
    @Test
    public void size() {
        List<Integer> lst = new LinkedList<>();
        assertEquals(0, lst.size());
    }

    @Test
    public void addOne() {
        List<Integer> lst = new LinkedList<>();
        lst.add(10);
        assertEquals(1, lst.size());
        assertEquals(10, lst.get(0));
    }

    @Test
    public void addTen() {
        List<Integer> lst = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            lst.add(i);
        }

        assertEquals(10, lst.size());
        assertEquals(9, lst.get(9));
    }

    @Test
    public void removeOne() {
        List<Integer> lst = new LinkedList<>();

        for (int i = 0; i < 3; i++) {
            lst.add(i);
        }

        assertEquals(1, lst.remove(1));
        assertEquals(2, lst.size());
        assertEquals(2, lst.get(1));
    }
}
