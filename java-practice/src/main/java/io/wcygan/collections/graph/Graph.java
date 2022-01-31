package io.wcygan.collections.graph;

import java.util.Set;

public interface Graph<T> {
    public Vertex<T> addVertex(T data);

    public Vertex<T> addVertex(Vertex<T> vertex);

    public Vertex<T> addNamedVertex(T data, String name);

    public Edge<T> addEdge(Vertex<T> source, Vertex<T> target);

    public Edge<T> addWeightedEdge(Vertex<T> source, Vertex<T> target, Double weight);

    public boolean containsVertex(Vertex<T> vertex);

    public boolean containsVertex(String vertexName);

    public boolean containsEdge(Vertex<T> source, Vertex<T> target);

    public default boolean containsEdge(Edge<T> edge) {
        return containsEdge(edge.source(), edge.target());
    }

    public Vertex<T> getVertex(String name);

    public Edge<T> getEdge(Vertex<T> source, Vertex<T> target);

    public Set<Edge<T>> incomingEdgesOf(Vertex<T> vertex);

    public Set<Edge<T>> outgoingEdgesOf(Vertex<T> vertex);

    public Set<Vertex<T>> vertexSet();

    public Set<Edge<T>> edgeSet();
}
