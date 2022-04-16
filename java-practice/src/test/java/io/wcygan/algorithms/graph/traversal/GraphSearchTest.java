package io.wcygan.algorithms.graph.traversal;

import io.wcygan.collections.graph.GraphBuilder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GraphSearchTest {

    private static Stream<Arguments> graphSearchData() {
        return Stream.of(
                Arguments.of(
                        // The starting vertex
                        1,
                        // The ending vertex
                        3,
                        // The edges in the graph
                        new Integer[][] {
                            {1, 2}, {2, 3},
                        },
                        // Is the ending vertex reachable from the starting vertex
                        true),
                Arguments.of(
                        // The starting vertex
                        1,
                        // The ending vertex
                        10,
                        // The edges in the graph
                        new Integer[][] {
                            {1, 2}, {2, 4}, {4, 6}, {6, 2}, {6, 3}, {3, 7}, {3, 8}, {8, 10},
                        },
                        // Is the ending vertex reachable from the starting vertex
                        true),
                Arguments.of(
                        // The starting vertex
                        1,
                        // The ending vertex
                        5,
                        // The edges in the graph
                        new Integer[][] {
                            {1, 2}, {2, 4}, {4, 6}, {6, 2}, {6, 3}, {3, 7}, {3, 8}, {8, 10},
                            {1, 3}, {2, 5}, {4, 7}, {6, 4}, {7, 4}, {10, 2}, {8, 5}, {8, 1},
                        },
                        // Is the ending vertex reachable from the starting vertex
                        true),
                Arguments.of(
                        // The starting vertex
                        1,
                        // The ending vertex
                        25,
                        // The edges in the graph
                        new Integer[][] {
                            {1, 2}, {2, 4}, {4, 6}, {6, 2}, {6, 3}, {3, 7}, {3, 8}, {8, 10},
                        },
                        // Is the ending vertex reachable from the starting vertex
                        false),
                Arguments.of(
                        // The starting vertex
                        1,
                        // The ending vertex
                        3,
                        // The edges in the graph
                        new Integer[][] {
                            {1, 2}, {2, 4},
                        },
                        // Is the ending vertex reachable from the starting vertex
                        false));
    }

    @ParameterizedTest
    @MethodSource("graphSearchData")
    public void testGraphSearch(Integer start, Integer end, Integer[][] edges, boolean reachable) {
        List.of(new BreadthFirstSearch<Integer>(), new DepthFirstSearch<Integer>())
                .forEach(search -> {
                    var graph = GraphBuilder.unweightedGraph(edges);
                    var entrypoint = graph.getVertex(Integer.toString(start));
                    assertNotNull(entrypoint);
                    assertEquals(
                            reachable,
                            search.isReachable(graph, entrypoint, end),
                            " during " + search.getClass().getName());
                });
    }
}
