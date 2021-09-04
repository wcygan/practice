package io.wcygan.collections.graph;

public record Edge<T>(Vertex<T> source,
                      Vertex<T> target,
                      Double weight) {

  public Vertex<T> getSource() {
    return source;
  }

  public Vertex<T> getTarget() {
    return target;
  }

  public Double weight() {
    return weight;
  }
}
