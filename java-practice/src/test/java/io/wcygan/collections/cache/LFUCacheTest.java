package io.wcygan.collections.cache;

import org.junit.Test;

import static org.junit.Assert.*;

public class LFUCacheTest {

    @Test
    public void entryEviction() {
        LFUCache<String, String> cache = new LFUCache<>(3);

        // set two keys
        assertTrue(cache.set("hello", "world"));
        assertTrue(cache.set("goodbye", "world"));

        // evict once
        assertTrue(cache.evictOneEntry());
        assertFalse(cache.get("hello").isPresent());

        // evict twice
        assertTrue(cache.evictOneEntry());
        assertFalse(cache.get("goodbye").isPresent());
    }

    @Test
    public void size() {
        LFUCache<String, String> cache = new LFUCache<>(3);
        cache.set("hello", "world");
        assertEquals(1, cache.size());
        cache.set("hello", "world");
        assertEquals(1, cache.size());
        cache.set("goodbye", "world");
        assertEquals(2, cache.size());
        cache.set("george", "washington");
        assertEquals(3, cache.size());
    }

    @Test
    public void setEvictsEntry() {
        LFUCache<String, String> cache = new LFUCache<>(1);
        assertTrue(cache.set("hello", "world"));
        assertTrue(cache.set("goodbye", "world"));
        assertFalse(cache.get("hello").isPresent());
        assertTrue(cache.get("goodbye").isPresent());
    }

    @Test
    public void setMultipleTimesAndEvict() {
        LFUCache<String, String> cache = new LFUCache<>(1);
        for (int i = 0; i < 10; i++) {
            assertTrue(cache.set("hello", "world"));
        }
        assertTrue(cache.set("goodbye", "world"));
        assertFalse(cache.get("hello").isPresent());
    }

    @Test
    public void evictOnEverySet() {
        LFUCache<String, String> cache = new LFUCache<>(1);
        assertTrue(cache.set("hello", "world"));
        assertTrue(cache.set("goodbye", "world"));
        assertFalse(cache.get("hello").isPresent());
        assertTrue(cache.set("hello", "world"));
        assertFalse(cache.get("goodbye").isPresent());
        assertTrue(cache.set("bonjour", "world"));
        assertFalse(cache.get("hello").isPresent());
    }

    @Test
    public void entriesAreNotEvictedWithEnoughCapacity() {
        LFUCache<String, String> cache = new LFUCache<>(10);
        assertTrue(cache.set("hello", "world"));
        assertTrue(cache.set("goodbye", "world"));
        assertTrue(cache.get("hello").isPresent());
        assertTrue(cache.set("hello", "world"));
        assertTrue(cache.get("hello").isPresent());
        assertTrue(cache.get("goodbye").isPresent());
        assertTrue(cache.set("bonjour", "world"));
        assertTrue(cache.get("hello").isPresent());
        assertTrue(cache.get("goodbye").isPresent());
        assertTrue(cache.get("bonjour").isPresent());
    }
}
