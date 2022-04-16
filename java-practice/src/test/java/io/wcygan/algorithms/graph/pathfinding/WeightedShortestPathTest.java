package io.wcygan.algorithms.graph.pathfinding;

import io.wcygan.collections.graph.Graph;
import io.wcygan.collections.graph.SimpleGraph;
import io.wcygan.collections.graph.Vertex;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class WeightedShortestPathTest {

    private static Stream<Arguments> weightedShortestPathGraphProvider() {
        return Stream.of(
                Arguments.of(
                        // The starting vertex
                        1,
                        // The ending vertex
                        3,
                        // The edges in the graph
                        new Integer[][] {{1, 2, 1}, {2, 3, 1}, {1, 3, 10}},
                        // The expected path
                        new Integer[][] {{1, 2, 1}, {2, 3, 1}}),
                Arguments.of(
                        // The starting vertex
                        1,
                        // The ending vertex
                        10,
                        // The edges in the graph
                        new Integer[][] {
                            {1, 2, 10},
                            {2, 3, 10},
                            {3, 4, 10},
                            {4, 5, 10},
                            {5, 6, 10},
                            {6, 7, 10},
                            {7, 8, 10},
                            {8, 9, 10},
                            {9, 10, 10},
                            {1, 3, 2},
                            {3, 5, 3},
                            {5, 8, 4},
                            {8, 9, 5}
                        },
                        // The expected path
                        new Integer[][] {{1, 3, 2}, {3, 5, 3}, {5, 8, 4}, {8, 9, 10}, {9, 10, 10}}));
    }

    @ParameterizedTest
    @MethodSource("weightedShortestPathGraphProvider")
    public void testWeightedShortestPath(int start, int end, Integer[][] edges, Integer[][] expectedEdges) {
        Graph<Integer> initial = buildGraph(edges);

        Vertex<Integer> startingVertex = initial.getVertex(Integer.toString(start));
        Vertex<Integer> endingVertex = initial.getVertex(Integer.toString(end));

        Graph<Integer> actual = ShortestPath.Dijkstra(initial, startingVertex, endingVertex);
        Graph<Integer> expected = buildGraph(expectedEdges);

        Helpers.validateShortestPath(actual, expected, start, end, expectedEdges.length);
    }

    private <T> Graph<T> buildGraph(T[][] edges) {
        Graph<T> graph = new SimpleGraph<>();

        for (T[] edge : edges) {
            T first = edge[0];
            T second = edge[1];
            double weight = Integer.valueOf((int) edge[2]).doubleValue();

            Vertex<T> from = graph.addNamedVertex(first, first.toString());
            Vertex<T> to = graph.addNamedVertex(second, second.toString());

            graph.addWeightedEdge(from, to, weight);
        }

        return graph;
    }
}
