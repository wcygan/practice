package io.wcygan.modern;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/**
 * https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
 */
public class StreamUtils {
    public static <T> List<T> filter(Collection<T> collection, Predicate<T> predicate) {
        return collection.stream().filter(predicate).toList();
    }
}
