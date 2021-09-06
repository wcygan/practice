package io.wcygan.modern;

import java.util.Optional;
import java.util.function.Predicate;

public class OptionalUtils {
  public static <T> Optional<T> filter(Optional<T> optional, Predicate<T> predicate) {
    return optional.filter(predicate);
  }
}
