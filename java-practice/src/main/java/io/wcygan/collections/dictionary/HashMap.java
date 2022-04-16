package io.wcygan.collections.dictionary;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.concurrent.ThreadSafe;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

@ThreadSafe
public class HashMap<K extends Comparable<K>, V> implements Map<K, V> {

    private static final Integer BUCKET_COUNT = 2 << 7;
    private final List<LinkedList<Pair<K, V>>> buckets;
    private final ReentrantReadWriteLock.ReadLock readLock;
    private final ReentrantReadWriteLock.WriteLock writeLock;

    public HashMap() {
        var lock = new ReentrantReadWriteLock();
        this.readLock = lock.readLock();
        this.writeLock = lock.writeLock();

        buckets = new ArrayList<>();
        for (int i = 0; i < BUCKET_COUNT; i++) {
            buckets.add(new LinkedList<>());
        }
    }

    private static int hash(Object key) {
        int h;
        return (key == null) ? 0 : Math.abs(((h = key.hashCode()) ^ (h >>> 16)) % BUCKET_COUNT);
    }

    @Override
    public int size() {
        readLock.lock();
        try {
            return buckets.stream().map(LinkedList::size).reduce(0, Integer::sum);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        readLock.lock();
        try {
            return size() == 0;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean containsKey(K key) {
        readLock.lock();
        try {
            var bucket = buckets.get(hash(key));
            for (var entry : bucket) {
                if (entry.getKey().equals(key)) {
                    return true;
                }
            }
            return false;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public V get(K key) {
        readLock.lock();
        try {
            var bucket = buckets.get(hash(key));
            for (var entry : bucket) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
            return null;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public V put(K key, V value) {
        writeLock.lock();
        try {
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
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public V remove(K key) {
        writeLock.lock();
        try {
            var bucket = buckets.get(hash(key));

            for (int i = 0; i < bucket.size(); i++) {
                if (bucket.get(i).getKey().equals(key)) {
                    return bucket.remove(i).getValue();
                }
            }

            return null;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void clear() {
        writeLock.lock();
        try {
            for (var bucket : buckets) {
                bucket.clear();
            }
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public Set<K> keySet() {
        readLock.lock();
        try {
            return entrySet().stream().map(Pair::getKey).collect(Collectors.toSet());
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Collection<V> values() {
        readLock.lock();
        try {
            return entrySet().stream().map(Pair::getValue).toList();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Set<Pair<K, V>> entrySet() {
        readLock.lock();
        try {
            return buckets.stream().flatMap(LinkedList::stream).collect(Collectors.toSet());
        } finally {
            readLock.unlock();
        }
    }
}
