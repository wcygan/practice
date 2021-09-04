package io.wcygan;

import com.google.common.annotations.VisibleForTesting;

public class App {
  private static final Integer MAX_VALUE = 1000;

  public static void main(String[] args) {
    System.out.println("Hello World!");
  }

  @VisibleForTesting
  public static String concat(String... args) {
    return String.join("", args);
  }

  @VisibleForTesting
  public static void enterMaze(int v) {
    if (isEqualToOne(v)) {
      System.out.println("Hello!");
    } else {
      if (even(v)) {
        if (greaterThanHalf(v)) {
          System.out.println("Bonjour!");
        } else {
          System.out.println("Aloha!");
        }
      } else {
        if (greaterThanHalf(v)) {
          System.out.println("Namaste!");
        } else {
          System.out.println("Guten tag!");
        }
      }
    }
  }

  private static boolean even(int v) {
    return v % 2 == 0;
  }

  private static boolean greaterThanHalf(int v) {
    return v > (MAX_VALUE / 2);
  }

  private static boolean isEqualToOne(int v) {
    return v == 1;
  }
}
