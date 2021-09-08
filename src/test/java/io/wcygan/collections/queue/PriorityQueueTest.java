package io.wcygan.collections.queue;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)

public class PriorityQueueTest {

  @Property(trials = 50)
  public void testShifts(@InRange(minInt = 0, maxInt = 25) int idx) {
    Assertions.assertEquals(expectedParent(idx), PriorityQueue.parent(idx));
    Assertions.assertEquals(expectedLeftChild(idx), PriorityQueue.leftChild(idx));
    Assertions.assertEquals(expectedRightChild(idx), PriorityQueue.rightChild(idx));
  }

  private int expectedParent(int i) {
    return i / 2;
  }

  private int expectedLeftChild(int i) {
    return (2 * i);
  }

  private int expectedRightChild(int i) {
    return (2 * i) + 1;
  }
}
