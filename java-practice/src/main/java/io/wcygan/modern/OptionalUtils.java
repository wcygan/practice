package io.wcygan.modern;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Optional.html
 */
public class OptionalUtils {
    public static <T> Optional<T> filter(Optional<T> optional, Predicate<T> predicate) {
        return optional.filter(predicate);
    }
}
