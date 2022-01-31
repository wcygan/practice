package io.wcygan.collections.graph;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleGraph<T> implements Graph<T> {

    private static final Double DEFAULT_WEIGHT = 1.0;

    int VERTEX_COUNTER = 1;
    Map<String, Vertex<T>> vertices = new HashMap<>();
    Map<Vertex<T>, Map<Vertex<T>, Edge<T>>> edges = new HashMap<>();

    @Override
    public Vertex<T> addVertex(T data) {
        return addNamedVertex(data, Integer.toString(VERTEX_COUNTER));
    }

    @Override
    public Vertex<T> addVertex(Vertex<T> vertex) {
        return addNamedVertex(vertex.getData(), vertex.getName());
    }

    @Override
    public Vertex<T> addNamedVertex(T data, String name) {
        if (vertices.containsKey(name)) {
            return vertices.get(name);
        }

        Vertex<T> vertex = new Vertex<>(data, name);
        vertices.put(name, vertex);
        VERTEX_COUNTER++;
        return vertex;
    }

    @Override
    public Edge<T> addEdge(Vertex<T> source, Vertex<T> target) {
        if (this.edges.containsKey(source) && this.edges.get(source).containsKey(target)) {
            return this.edges.get(source).get(target);
        }

        return addWeightedEdge(source, target, DEFAULT_WEIGHT);
    }

    @Override
    public Edge<T> addWeightedEdge(Vertex<T> source, Vertex<T> target, Double weight) {
        if (this.edges.containsKey(source) && this.edges.get(source).containsKey(target)) {
            return this.edges.get(source).get(target);
        }

        Edge<T> edge = new Edge<>(source, target, weight);
        this.edges.putIfAbsent(source, new HashMap<>());
        this.edges.get(source).put(target, edge);
        return edge;
    }

    @Override
    public boolean containsVertex(Vertex<T> vertex) {
        return vertices.containsKey(vertex.getName());
    }

    @Override
    public boolean containsVertex(String vertexName) {
        return vertices.containsKey(vertexName);
    }

    @Override
    public boolean containsEdge(Vertex<T> source, Vertex<T> target) {
        return edges.containsKey(source) && edges.get(source).containsKey(target);
    }

    @Override
    public Vertex<T> getVertex(String name) {
        return vertices.get(name);
    }

    @Override
    public Edge<T> getEdge(Vertex<T> source, Vertex<T> target) {
        return edges.get(source).get(target);
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
        return new HashSet<>(this.vertices.values());
    }
}
