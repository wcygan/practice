package io.wcygan.algorithms.graph.traversal;

import io.wcygan.collections.graph.Graph;
import io.wcygan.collections.graph.Vertex;

public class DepthFirstSearch<T> implements Reachable<T> {
    @Override
    public boolean isReachable(Graph<T> graph, Vertex<T> entrypoint, T dataToFind) {
        if (entrypoint == null) {
            return false;
        }

        if (!graph.containsVertex(entrypoint)) {
            return false;
        }

        return find(graph, entrypoint, dataToFind);
    }

    private boolean find(Graph<T> graph, Vertex<T> entrypoint, T data) {
        entrypoint.setVisited(true);
        if (entrypoint.getData().equals(data)) {
            return true;
        }

        for (var edge : graph.outgoingEdgesOf(entrypoint)) {
            var vtx = edge.target();
            if (!vtx.isVisited()) {
                if (find(graph, vtx, data)) {
                    return true;
                }
            }
        }

        return false;
    }
}
