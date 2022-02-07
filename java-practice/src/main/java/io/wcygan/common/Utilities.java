package io.wcygan.common;

import static org.apache.commons.lang3.compare.ComparableUtils.is;

public class Utilities {
    public static <T extends Comparable<T>> boolean isLessThan(T left, T right) {
        return is(left).lessThan(right);
    }
}
