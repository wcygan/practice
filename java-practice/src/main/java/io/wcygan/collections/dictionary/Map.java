package io.wcygan.collections.dictionary;

import java.util.Collection;
import java.util.Set;

public interface Map<K, V> {

    int size();

    boolean isEmpty();

    boolean containsKey(Object key);

    V get(Object key);

    V put(K key, V value);

    V remove(Object key);

    void clear();

    Set<K> keySet();

    Collection<V> values();

    default V putIfAbsent(K key, V value) {
        V v = get(key);
        if (v == null) {
            v = put(key, value);
        }

        return v;
    }
}
