package io.wcygan.reflect;

import java.util.*;

public class Inspector {
    public static Map<Class<?>, Class<?>> classHierarchyOf(Class<?> root) {
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

    public static Set<Class<?>> interfacesOf(Class<?> root) {
        var allInterfaces = new HashSet<Class<?>>();

        if (root == null) {
            return allInterfaces;
        }

        Queue<Class<?>> remainder = new ArrayDeque<>();
        remainder.add(root);

        while (!remainder.isEmpty()) {
            Class<?> next = remainder.remove();

            Class<?>[] currentInterfaces = next.getInterfaces();
            for (Class<?> anInterface : currentInterfaces) {
                allInterfaces.add(anInterface);
                remainder.add(anInterface);
            }
        }

        return allInterfaces;
    }
}
