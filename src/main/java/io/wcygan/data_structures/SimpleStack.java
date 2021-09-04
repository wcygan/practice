package io.wcygan.data_structures;

import io.wcygan.abstract_data_types.Stack;

import java.util.LinkedList;
import java.util.List;

public class SimpleStack<T> implements Stack<T> {

  List<T> list;

  public SimpleStack() {
    list = new LinkedList<>();
  }

  /**
   * Adds data to the stack
   *
   * @param data the data to add to the stack
   */
  public void push(T data) {
    list.add(data);
  }

  /**
   * Returns the data at the top of the stack & removes it from the stack. If the stack is empty,
   * returns null
   *
   * @return T
   */
  public T pop() {
    return list.remove(0);
  }

  /**
   * Returns the data at the top of the stack. If the stack is empty, returns null
   *
   * @return T
   */
  public T peek() {
    return list.get(0);
  }

  /**
   * Returns how many items are in the stack
   *
   * @return int
   */
  public int size() {
    return list.size();
  }
}
