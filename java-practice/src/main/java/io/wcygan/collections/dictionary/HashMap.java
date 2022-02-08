package io.wcygan.collections.dictionary;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class HashMap<K extends Comparable<K>, V> implements Map<K, V> {

    private static final Integer BUCKET_COUNT = 2 << 7;
    private final List<LinkedList<Pair<K, V>>> buckets;

    public HashMap() {
        buckets = new ArrayList<>();
        for (int i = 0; i < BUCKET_COUNT; i++) {
            buckets.add(new LinkedList<>());
        }
    }


    @Override
    public int size() {
        return buckets.stream().map(LinkedList::size).reduce(0, Integer::sum);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(K key) {
        var bucket = buckets.get(hash(key));
        for (var entry : bucket) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        var bucket = buckets.get(hash(key));
        for (var entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        var bucket = buckets.get(hash(key));
        for (var entry : bucket) {
            if (entry.getKey().equals(key)) {
                var oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }

        bucket.add(MutablePair.of(key, value));
        return null;
    }

    @Override
    public V remove(K key) {
        var bucket = buckets.get(hash(key));

        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i).getKey().equals(key)) {
                return bucket.remove(i).getValue();
            }
        }

        return null;
    }

    @Override
    public void clear() {
        for (var bucket : buckets) {
            bucket.clear();
        }
    }

    @Override
    public Set<K> keySet() {
        return entrySet().stream().map(Pair::getKey).collect(Collectors.toSet());
    }

    @Override
    public Collection<V> values() {
        return entrySet().stream().map(Pair::getValue).toList();
    }

    @Override
    public Set<Pair<K, V>> entrySet() {
        return buckets.stream().flatMap(LinkedList::stream).collect(Collectors.toSet());
    }

    static int hash(Object key) {
        int h;
        return (key == null) ? 0 : Math.abs(((h = key.hashCode()) ^ (h >>> 16)) % BUCKET_COUNT);
    }
}
