package io.wcygan.collections.dictionary;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DictionaryTest {

    private static Stream<Arguments> mapProvider() {
        return Stream.of(
                Arguments.of(new TreeMap<String, String>())
        );
    }

    @ParameterizedTest
    @MethodSource("mapProvider")
    public void testHappyPath(Map<String, String> map) {
        var k1 = "Hello";
        var k2 = "Bonjour";
        var v1 = "World";
        var v2 = "Universe";
        assertNull(map.put(k1, v1));
        assertEquals(v1, map.put(k1, v2));
        assertEquals(v2, map.get(k1));
        assertEquals(1, map.size());
        assertNull(map.put(k2, v1));
        assertEquals(v1, map.get(k2));
        assertEquals(2, map.size());
        map.clear();
        assertEquals(0, map.size());
        assertNull(map.get(k1));
        assertNull(map.get(k2));
    }
}
