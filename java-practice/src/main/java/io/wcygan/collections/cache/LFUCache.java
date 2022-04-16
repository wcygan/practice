package io.wcygan.collections.cache;

import com.google.common.annotations.VisibleForTesting;

import java.util.Optional;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LFUCache<Key, Value> implements Cache<Key, Value> {
    private final int capacity;
    private ConcurrentHashMap<Key, CacheItem> items;
    private PriorityQueue<CacheItem> keyPriorities;
    private ReentrantReadWriteLock.ReadLock readLock;
    private ReentrantReadWriteLock.WriteLock writeLock;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.items = new ConcurrentHashMap<>();
        this.keyPriorities = new PriorityQueue<>(CacheItem::compareTo);
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        this.readLock = lock.readLock();
        this.writeLock = lock.writeLock();
    }

    @Override
    public boolean set(Key key, Value value) {
        writeLock.lock();
        try {
            if (this.items.contains(key)) {
                CacheItem item = items.remove(key);
                keyPriorities.remove(item);
                item.counter.incrementAndGet();
                item.value = value;
                return keyPriorities.add(item);
            } else {
                if (this.size() >= this.capacity) {
                    this.evictOneEntry();
                }

                CacheItem cacheItem = new CacheItem(key, value);

                if (!keyPriorities.add(cacheItem)) {
                    return false;
                }

                items.put(key, cacheItem);
            }
            return true;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public Optional<Value> get(Key key) {
        readLock.lock();
        try {
            return Optional.ofNullable(items.get(key)).map(item -> {
                keyPriorities.remove(item);
                item.counter.incrementAndGet();
                keyPriorities.add(item);
                return item.value;
            });
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public int size() {
        readLock.lock();
        try {
            return items.size();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        readLock.lock();
        try {
            return items.size() == 0;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void clear() {
        writeLock.lock();
        try {
            items.clear();
            keyPriorities.clear();
        } finally {
            writeLock.unlock();
        }
    }

    @VisibleForTesting
    protected boolean evictOneEntry() {
        writeLock.lock();
        try {
            Optional<CacheItem> item = Optional.ofNullable(keyPriorities.remove());
            if (item.isEmpty()) {
                return false;
            }
            items.remove(item.get().key);
            return true;
        } finally {
            writeLock.unlock();
        }
    }

    private class CacheItem implements Comparable<CacheItem> {
        public Key key;
        public Value value;
        public AtomicInteger counter;

        private CacheItem(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.counter = new AtomicInteger(1);
        }

        @Override
        public int compareTo(CacheItem o) {
            if (o == null) {
                return -1;
            }
            return this.counter.get() - o.counter.get();
        }
    }
}
