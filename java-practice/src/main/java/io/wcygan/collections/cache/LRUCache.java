package io.wcygan.collections.cache;

import com.google.common.annotations.VisibleForTesting;
import io.wcygan.collections.list.LinkedList;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// adapted from https://github.com/mlarocca/AlgorithmsAndDataStructuresInAction
@ThreadSafe
public class LRUCache<Key, Value> implements Cache<Key, Value> {

    private final int maxSize;
    private final ConcurrentHashMap<Key, LinkedList.LinkedListNode<CacheItem>> nodes;
    private final LinkedList<CacheItem> itemsList;
    private final ReentrantReadWriteLock.ReadLock readLock;
    private final ReentrantReadWriteLock.WriteLock writeLock;

    public LRUCache(int maxSize) {
        this.maxSize = maxSize;
        this.nodes = new ConcurrentHashMap<>(maxSize);
        this.itemsList = new LinkedList<>();
        var lock = new ReentrantReadWriteLock();
        this.readLock = lock.readLock();
        this.writeLock = lock.writeLock();
    }

    @Override
    public boolean set(Key key, Value value) {
        writeLock.lock();
        try {
            var item = new CacheItem(key, value);

            if (this.nodes.containsKey(key)) {
                var node = this.nodes.get(key);

                // pluck the node from the list, update its contents, and bring it to the front of the list
                var newNode = itemsList.updateAndBringToFront(node, item);

                if (newNode.isEmpty()) {
                    return false;
                }

                // update the cache's book-keeping to track the new node
                this.nodes.put(key, newNode);
                return true;
            }

            // too many items in the cache? evict one!
            if (this.size() >= this.maxSize) {
                this.evictOneEntry();
            }

            // add our item to the cache
            var newNode = this.itemsList.add(item);
            if (newNode.isEmpty()) {
                return false;
            }

            this.nodes.put(key, newNode);
            return true;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public Optional<Value> get(Key key) {
        readLock.lock();
        try {
            var node = this.nodes.get(key);
            if (node == null || node.isEmpty()) {
                return Optional.empty();
            }

            nodes.put(key, this.itemsList.bringToFront(node));
            return Optional.of(node.getValue().value);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public int size() {
        readLock.lock();
        try {
            return itemsList.size();
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
    public void clear() {
        writeLock.lock();
        try {
            nodes.clear();
            itemsList.clear();
        } finally {
            writeLock.unlock();
        }
    }

    @VisibleForTesting
    protected boolean evictOneEntry() {
        writeLock.lock();
        try {
            LinkedList.LinkedListNode<CacheItem> node = itemsList.removeTail();
            if (node.isEmpty()) {
                // Cache was empty
                return false;
            }
            nodes.remove(node.getValue().key);
            return true;
        } finally {
            writeLock.unlock();
        }
    }

    private class CacheItem {
        public Key key;
        public Value value;

        private CacheItem(Key key, Value value) {
            this.value = value;
            this.key = key;
        }
    }
}
