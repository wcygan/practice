package io.wcygan.abstract_data_types;

public interface Stack<T> {
  public void push(T data);

  public T pop();

  public T peek();

  public int size();
}
