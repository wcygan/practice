package io.wcygan.collections.cache;

import java.util.Optional;

public class LFUCache<Key, Value> implements Cache<Key, Value> {
    @Override
    public boolean set(Key key, Value value) {
        throw new Error("Not Implemented");
    }

    @Override
    public Optional<Value> get(Key key) {
        throw new Error("Not Implemented");
    }

    @Override
    public int size() {
        throw new Error("Not Implemented");
    }

    @Override
    public boolean isEmpty() {
        throw new Error("Not Implemented");
    }

    @Override
    public void clear() {
        throw new Error("Not Implemented");
    }
}
