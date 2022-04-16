package io.wcygan.algorithms.graph.pathfinding;

import io.wcygan.collections.graph.Edge;
import io.wcygan.collections.graph.Graph;
import io.wcygan.collections.graph.Vertex;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class Helpers {

    private static final double THRESHOLD = 0.001;

    public static void validateShortestPath(
            Graph<Integer> actualPath,
            Graph<Integer> expectedPath,
            int startVertex,
            int endVertex,
            int expectedPathLength) {

        assertEquals(expectedPath.vertexSet().size(), actualPath.vertexSet().size());
        assertEquals(expectedPath.edgeSet().size(), actualPath.edgeSet().size());

        /* walk the shortest path and verify equality */
        Vertex<Integer> a = actualPath.getVertex(Integer.toString(startVertex));
        Vertex<Integer> b = expectedPath.getVertex(Integer.toString(startVertex));

        for (int i = 0; i <= expectedPathLength; i++) {
            assertNotNull(a);
            assertNotNull(b);
            assertEquals(a.getData(), b.getData());
            Optional<Edge<Integer>> nextA =
                    actualPath.outgoingEdgesOf(a).stream().findFirst();
            Optional<Edge<Integer>> nextB =
                    expectedPath.outgoingEdgesOf(b).stream().findFirst();

            if (nextA.isPresent() && nextB.isPresent()) {
                a = nextA.get().target();
                b = nextB.get().target();
            } else {
                if (nextA.isEmpty()) {
                    assertEquals(endVertex, a.getData());
                    a = null;
                }
                if (nextB.isEmpty()) {
                    assertEquals(endVertex, b.getData());
                    b = null;
                }
            }
        }

        double actualPathCost =
                actualPath.edgeSet().stream().mapToDouble(Edge::weight).sum();
        double expectedPathCost =
                expectedPath.edgeSet().stream().mapToDouble(Edge::weight).sum();

        /* make sure the paths have the same cost */
        assertTrue(Math.abs(actualPathCost - expectedPathCost) < THRESHOLD);

        /* make sure a and b are null after traversal */
        assertNull(a);
        assertNull(b);
    }
}
