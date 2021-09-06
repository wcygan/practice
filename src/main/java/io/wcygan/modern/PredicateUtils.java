package io.wcygan.modern;

import java.util.function.Predicate;

public class PredicateUtils {
  public static final Predicate<Integer> IS_EVEN = (n) -> n % 2 == 0;
  public static final Predicate<Integer> IS_ODD = (n) -> n % 2 != 0;
}
