package io.wcygan.reflect;

import java.util.HashMap;
import java.util.Map;

public class Hierarchy {
    public static Map<Class<?>, Class<?>> find(Class<?> root) {
        var hierarchy = new HashMap<Class<?>, Class<?>>();

        if (root == null) {
            return hierarchy;
        }

        while (root.getSuperclass() != null) {
            hierarchy.put(root, root.getSuperclass());
            root = root.getSuperclass();
        }

        return hierarchy;
    }
}
