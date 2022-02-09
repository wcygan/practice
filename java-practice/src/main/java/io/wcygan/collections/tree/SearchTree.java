package io.wcygan.collections.tree;

public interface SearchTree<Key extends Comparable<Key>, Value> {
    /**
     * Searches the tree for a given key
     *
     * @param key the key to search for
     * @return the value to which the specified key is currently associated with,
     * or {@code null} if this map contains no mapping for the key
     */
    Value search(Key key);

    /**
     * Inserts the key, value pair into the tree. If the specified key already exists,
     * the old value is replaced by the new value and the old value is returned.
     *
     * @param key   the key to search for
     * @param value the value to be associated with the specified key
     * @return the value to which the specified key is currently associated with,
     * or {@code null} if this map contains no mapping for the key
     */
    Value insert(Key key, Value value);

    /**
     * Removes the key, value pair from the tree if the specified key exists
     *
     * @param key the key to search for
     * @return the value to which the specified key is currently associated with,
     * or {@code null} if this map contains no mapping for the key
     */
    Value remove(Key key);

    /**
     * Determines if the tree contains the specified key
     *
     * @param key the key to search for
     *            Returns {@code true} if and only if the tree contains a mapping
     *            for a key {@code k} such that {@code Objects.equals(key, k)}.
     */
    boolean containsKey(Key key);
}
