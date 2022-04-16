package io.wcygan.common;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.apache.commons.lang3.compare.ComparableUtils.is;

public class Utilities {
    public static <T extends Comparable<T>> boolean isEqual(T left, T right) {
        return is(left).equalTo(right);
    }

    public static <T extends Comparable<T>> boolean isLessThan(T left, T right) {
        return is(left).lessThan(right);
    }

    public static <T extends Comparable<T>> List<T> uniques(List<T> lst) {
        return new HashSet<>(lst).stream().toList();
    }

    public static List<String> randomUniqueStrings(int size) {
        return IntStream.range(0, size)
                .mapToObj(idx -> RandomStringUtils.random(25, true, true))
                .collect(Collectors.toSet())
                .stream()
                .toList();
    }
}
