package io.wcygan.collections.dictionary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DictionaryTest {

    @Test
    public void testHappyPath() {
        var map = new TreeMap<String, String>();
        var k1 = "Hello";
        var k2 = "Bonjour";
        var v1 = "World";
        var v2 = "Universe";
        assertNull(map.put(k1, v1));
        assertEquals(v1, map.put(k1, v2));
        assertEquals(v2, map.get(k1));
        assertNull(map.put(k2, v1));
        assertEquals(v1, map.get(k2));
    }
}
