package io.wcygan.collections.graph;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleGraph<T> implements Graph<T> {

  private static final Double DEFAULT_WEIGHT = 1.0;

  int VERTEX_COUNTER = 0;
  Set<Vertex<T>> vertices = new HashSet<>();
  Map<Vertex<T>, Map<Vertex<T>, Edge<T>>> edges = new HashMap<>();

  @Override
  public Vertex<T> addVertex(T data) {
    return addNamedVertex(data, Integer.toString(VERTEX_COUNTER));
  }

  @Override
  public Vertex<T> addNamedVertex(T data, String name) {
    Vertex<T> vertex = new Vertex<>(data, name);
    vertices.add(vertex);
    VERTEX_COUNTER++;
    return vertex;
  }

  @Override
  public Edge<T> addEdge(Vertex<T> source, Vertex<T> target) {
    Edge<T> edge = new Edge<>(source, target, DEFAULT_WEIGHT);
    edges.putIfAbsent(source, new HashMap<>());
    edges.get(source).put(target, edge);
    return edge;
  }

  @Override
  public boolean containsVertex(Vertex<T> vertex) {
    return vertices.contains(vertex);
  }

  @Override
  public boolean containsEdge(Vertex<T> source, Vertex<T> target) {
    return edges.containsKey(source) && edges.get(source).containsKey(target);
  }

  @Override
  public Set<Edge<T>> incomingEdgesOf(Vertex<T> target) {
    /* expensive method... */
    return this.edges.values().stream()
        .filter(m -> m.containsKey(target))
        .map(m -> m.get(target))
        .collect(Collectors.toSet());
  }

  @Override
  public Set<Edge<T>> outgoingEdgesOf(Vertex<T> source) {
    return (this.edges.containsKey(source))
        ? new HashSet<>(this.edges.get(source).values())
        : new HashSet<>();
  }

  @Override
  public Set<Edge<T>> edgeSet() {
    return this.edges.values().stream()
        .map(Map::values)
        .flatMap(Collection::stream)
        .collect(Collectors.toSet());
  }

  @Override
  public Set<Vertex<T>> vertexSet() {
    /* return a new hashset to prohibit access to inner data */
    return new HashSet<>(this.vertices);
  }
}
