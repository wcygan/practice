package io.wcygan.collections.dictionary;

import io.wcygan.collections.tree.LLRedBlackTree;
import io.wcygan.collections.tree.SearchTree;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class TreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    SearchTree<K, V> tree = new LLRedBlackTree<>();

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public boolean containsKey(K key) {
        return tree.containsKey(key);
    }

    @Override
    public V get(K key) {
        return tree.search(key);
    }

    @Override
    public V put(K key, V value) {
        return tree.insert(key, value);
    }

    @Override
    public V remove(K key) {
        return tree.remove(key);
    }

    @Override
    public void clear() {
        tree.entrySet().forEach(e -> tree.remove(e.getKey()));
    }

    @Override
    public Set<K> keySet() {
        return tree.entrySet().stream().map(Pair::getKey).collect(Collectors.toSet());
    }

    @Override
    public Collection<V> values() {
        return tree.entrySet().stream().map(Pair::getValue).toList();
    }

    @Override
    public Set<Pair<K, V>> entrySet() {
        return tree.entrySet();
    }
}
