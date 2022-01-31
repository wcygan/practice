package io.wcygan.modern;

import java.util.function.Predicate;

/**
 * https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/function/Predicate.html
 */
public class PredicateUtils {
    public static final Predicate<Integer> IS_EVEN = (n) -> n % 2 == 0;
    public static final Predicate<Integer> IS_ODD = (n) -> n % 2 != 0;
}
