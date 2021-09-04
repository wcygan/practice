package io.wcygan.collections.graph;

import java.util.Set;

public interface Graph<T> {
  public Vertex<T> addVertex(T data);

  public Vertex<T> addNamedVertex(T data, String name);

  public Edge<T> addEdge(Vertex<T> source, Vertex<T> target);

  public boolean containsVertex(Vertex<T> vertex);

  public boolean containsEdge(Vertex<T> source, Vertex<T> target);

  public default boolean containsEdge(Edge<T> edge) {
    return containsEdge(edge.source(), edge.target());
  }

  public Set<Edge<T>> incomingEdgesOf(Vertex<T> vertex);

  public Set<Edge<T>> outgoingEdgesOf(Vertex<T> vertex);

  public Set<Vertex<T>> vertexSet();

  public Set<Edge<T>> edgeSet();
}
