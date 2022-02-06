package io.wcygan.algorithms.graph.traversal;

import io.wcygan.collections.graph.Edge;
import io.wcygan.collections.graph.Graph;
import io.wcygan.collections.graph.Vertex;

import java.util.HashSet;

public class BreadthFirstSearch<T> implements Reachable<T> {

    @Override
    public boolean isReachable(Graph<T> graph, Vertex<T> entrypoint, T dataToFind) {
        if (entrypoint == null) {
            return false;
        }

        if (!graph.containsVertex(entrypoint)) {
            return false;
        }

        if (entrypoint.getData().equals(dataToFind)) {
            return true;
        }

        return find(graph, entrypoint, dataToFind);
    }

    private boolean find(Graph<T> graph, Vertex<T> entrypoint, T data) {
        entrypoint.setVisited(true);
        var current = new HashSet<>(graph.outgoingEdgesOf(entrypoint));
        var next = new HashSet<Edge<T>>();

        while (!current.isEmpty()) {
            for (var edge : current) {
                var vtx = edge.target();
                if (vtx.isVisited()) {
                    continue;
                }

                if (vtx.getData().equals(data)) {
                    return true;
                }

                vtx.setVisited(true);
                next.addAll(graph.outgoingEdgesOf(vtx));
            }

            current.clear();
            current.addAll(next);
            next.clear();
        }

        return false;
    }
}
