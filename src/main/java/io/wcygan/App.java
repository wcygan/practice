package io.wcygan;

import com.google.common.annotations.VisibleForTesting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
  public static final Integer MAX_VALUE = 1000;

  public static void main(String[] args) {
    LOGGER.info("Hello, World!");
  }

  @VisibleForTesting
  public static void enterMaze(int v) {
    if (isEqualToOne(v)) {
      LOGGER.debug("Hello!");
    } else {
      if (even(v)) {
        if (greaterThanHalf(v)) {
          LOGGER.debug("Bonjour!");
        } else {
          LOGGER.debug("Aloha!");
        }
      } else {
        if (greaterThanHalf(v)) {
          LOGGER.debug("Namaste!");
        } else {
          LOGGER.debug("Guten tag!");
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
