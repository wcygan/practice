package io.wcygan;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@RunWith(JUnitQuickcheck.class)
public class QuickCheckTest {
  @Property(trials = 10)
  public void testMaze(@InRange(minInt = 0, maxInt = 1000) int v) {
    assertDoesNotThrow(() -> App.enterMaze(v));
  }
}
