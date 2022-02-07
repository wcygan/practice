package io.wcygan.collections.dictionary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DictionaryTest {

    @Test
    public void testHappyPath() {
        var map = new TreeMap<String, String>();
        var k = "Hello";
        var v1 = "World";
        var v2 = "Test";
        assertNull(map.put(k, v1));
        assertEquals(v1, map.put(k, v2));
        assertEquals(v2, map.get(k));
    }
}
