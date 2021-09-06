package io.wcygan.modern;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class StreamUtils {
  public static <T> List<T> filter(Collection<T> collection, Predicate<T> predicate) {
    return collection.stream().filter(predicate).toList();
  }
}
