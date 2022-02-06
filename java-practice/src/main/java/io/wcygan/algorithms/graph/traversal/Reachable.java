package io.wcygan.algorithms.graph.traversal;

import io.wcygan.collections.graph.Graph;
import io.wcygan.collections.graph.Vertex;

public interface Reachable<T> {
    boolean isReachable(Graph<T> graph, Vertex<T> entrypoint, T data);
}
