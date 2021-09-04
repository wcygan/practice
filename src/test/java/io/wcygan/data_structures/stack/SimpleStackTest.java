package io.wcygan.data_structures.stack;

import io.wcygan.abstract_data_types.Stack;
import io.wcygan.data_structures.SimpleStack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleStackTest {

  @Test
  public void happyPath() {
    Stack<Integer> stack = new SimpleStack<>();
    assertEquals(0, stack.size());
  }

  @Test
  public void addOneToStack() {
    Stack<Integer> stack = new SimpleStack<>();
    stack.push(999);
    assertEquals(1, stack.size());
  }

  @Test
  public void pushAndPop() {
    Stack<Integer> stack = new SimpleStack<>();
    stack.push(999);
    int i = stack.pop();
    assertEquals(0, stack.size());
    assertEquals(999, i);
  }
}
