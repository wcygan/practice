package io.wcygan;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AppTest {

  @Test
  public void itWorks() {
    assertDoesNotThrow(() -> App.main(new String[1]));
  }
}
