package io.wcygan.modern;

import java.util.HashMap;
import java.util.Map;

/**
 * https://developer.oracle.com/java/jdk-10-local-variable-type-inference.html
 */
public class VarUtils {
    public static <K, V> Map<K, V> createMap(K key, V value) {
        var map = new HashMap<K, V>();
        map.put(key, value);
        return map;
    }
}
