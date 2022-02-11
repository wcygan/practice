package io.wcygan.common;


import java.util.HashSet;
import java.util.List;

import static org.apache.commons.lang3.compare.ComparableUtils.is;

public class Utilities {
    public static <T extends Comparable<T>> boolean isLessThan(T left, T right) {
        return is(left).lessThan(right);
    }

    public static <T extends Comparable<T>> List<T> uniques(List<T> lst) {
        return new HashSet<>(lst).stream().toList();
    }
}
