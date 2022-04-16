package io.wcygan.collections.dictionary;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.Size;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnitQuickcheck.class)
public class DictionaryTest {

    private static Stream<Arguments> mapProvider() {
        return Stream.of(Arguments.of(new TreeMap<String, String>()), Arguments.of(new HashMap<String, String>()));
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
        assertEquals(List.of(v1, v2), map.values());
        assertEquals(Set.of(k1, k2), map.keySet());
        assertEquals(v2, map.remove(k1));
        assertEquals(v1, map.remove(k2));
        assertFalse(map.containsKey(k1));
        assertFalse(map.containsKey(k2));
        assertEquals(0, map.size());
        assertNull(map.get(k1));
        assertNull(map.get(k2));
    }

    @Property(trials = 25)
    public void testMapProperties(@Size(min = 10, max = 100) List<Integer> items) {
        var uniques = new HashSet<>(items).stream().toList();
        var v = "Hello World";

        List.of(new HashMap<Integer, String>(), new TreeMap<Integer, String>()).forEach(map -> {
            for (var k : uniques) {
                assertNull(map.put(k, v));
                assertTrue(map.containsKey(k));
            }

            assertEquals(uniques.size(), map.size());
            assertFalse(map.isEmpty());

            for (var k : uniques) {
                assertEquals(v, map.get(k));
                assertTrue(map.containsKey(k));
            }

            assertEquals(uniques.size(), map.size());
            assertFalse(map.isEmpty());

            for (var k : uniques) {
                var res = map.remove(k);
                assertNotNull(res);
                assertEquals(v, res);
                assertFalse(map.containsKey(k));
            }

            assertEquals(0, map.size());
            assertTrue(map.isEmpty());
        });
    }
}
