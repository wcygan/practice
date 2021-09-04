package io.wcygan.collections.graph;

public class Vertex<T> {
  private final T data;
  private final String name;
  private Color color;

  public Vertex(T data, String name) {
    this.data = data;
    this.name = name;
    this.color = Color.WHITE;
  }

  public T getData() {
    return data;
  }

  public String getName() {
    return name;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }
}
