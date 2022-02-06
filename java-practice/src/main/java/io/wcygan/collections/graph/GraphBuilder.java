package io.wcygan.collections.graph;

public class GraphBuilder {
    public static <T> Graph<T> unweightedGraph(T[][] edges) {
        Graph<T> graph = new SimpleGraph<>();

        for (T[] edge : edges) {
            T first = edge[0];
            T second = edge[1];

            Vertex<T> from = graph.addNamedVertex(first, first.toString());
            Vertex<T> to = graph.addNamedVertex(second, second.toString());

            graph.addEdge(from, to);
        }

        return graph;
    }
}
