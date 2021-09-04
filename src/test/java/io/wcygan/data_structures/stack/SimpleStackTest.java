package io.wcygan.data_structures.stack;

import io.wcygan.abstract_data_types.Stack;
import io.wcygan.data_structures.SimpleStack;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

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

  @ParameterizedTest
  @MethodSource("provideDataForAddMany")
  public void addMany(List<Integer> toAdd) {
    Stack<Integer> stack = new SimpleStack<>();
    toAdd.forEach(stack::push);
    assertEquals(toAdd.size(), stack.size());
  }

  private static Stream<List<Integer>> provideDataForAddMany() {
    return Stream.of(List.of(1), List.of(1, 2, 3), List.of(1, 2, 3, 3, 3, 3, 3), List.of());
  }
}
