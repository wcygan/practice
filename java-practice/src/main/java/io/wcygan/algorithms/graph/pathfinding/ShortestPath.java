package io.wcygan.algorithms.graph.pathfinding;

import io.wcygan.collections.graph.Edge;
import io.wcygan.collections.graph.Graph;
import io.wcygan.collections.graph.SimpleGraph;
import io.wcygan.collections.graph.Vertex;

import java.util.PriorityQueue;

public class ShortestPath {

    public static <T> Graph<T> Dijkstra(Graph<T> graph, Vertex<T> source, Vertex<T> destination) {
        initializeSingleSource(graph, source);

        PriorityQueue<Vertex<T>> waiting = new PriorityQueue<>(graph.vertexSet());
        while (!waiting.isEmpty()) {
            Vertex<T> u = waiting.remove();
            graph.outgoingEdgesOf(u).forEach(edge -> relax(edge, waiting));
        }

        return extractPredecessors(graph, source, destination);
    }

    private static <T> void initializeSingleSource(Graph<T> graph, Vertex<T> source) {
        graph.vertexSet().forEach(v -> {
            v.setCost(Double.POSITIVE_INFINITY);
            v.setPred(null);
        });
        source.setCost(0.0);
    }

    private static <T> void relax(Edge<T> edge, PriorityQueue<Vertex<T>> waiting) {
        Vertex<T> u = edge.source();
        Vertex<T> v = edge.target();
        double cost = u.getCost() + edge.weight();

        if (v.getCost() > cost) {
            /* relatively expensive heap operations */
            waiting.remove(v);
            v.setCost(cost);
            v.setPred(u);
            waiting.add(v);
        }
    }

    private static <T> Graph<T> extractPredecessors(Graph<T> parent, Vertex<T> source, Vertex<T> destination) {
        Graph<T> shortestPath = new SimpleGraph<>();
        Vertex<T> curr = destination;

        while (!curr.equals(source)) {
            Vertex<T> u = shortestPath.addVertex(curr.getPred());
            Vertex<T> v = shortestPath.addVertex(curr);
            double weight = parent.getEdge(parent.getVertex(u.getName()), parent.getVertex(v.getName()))
                    .weight();
            shortestPath.addWeightedEdge(u, v, weight);
            curr = curr.getPred();
        }

        return shortestPath;
    }
}
