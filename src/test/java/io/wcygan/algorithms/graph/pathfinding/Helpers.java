package io.wcygan.algorithms.graph.pathfinding;

import io.wcygan.collections.graph.Edge;
import io.wcygan.collections.graph.Graph;
import io.wcygan.collections.graph.Vertex;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class Helpers {

  private static final double THRESHOLD = 0.001;

  public static void validateGraphs(
      Graph<Integer> actual, Graph<Integer> expected, int start, int end, int expectedLength) {

    /* walk the shortest path and verify equality */
    Vertex<Integer> a = actual.getVertex(Integer.toString(start));
    Vertex<Integer> b = expected.getVertex(Integer.toString(start));

    for (int i = 0; i <= expectedLength; i++) {
      assertNotNull(a);
      assertNotNull(b);
      assertEquals(a.getData(), b.getData());
      Optional<Edge<Integer>> nextA = actual.outgoingEdgesOf(a).stream().findFirst();
      Optional<Edge<Integer>> nextB = expected.outgoingEdgesOf(b).stream().findFirst();

      if (nextA.isPresent() && nextB.isPresent()) {
        a = nextA.get().target();
        b = nextB.get().target();
      } else {
        if (nextA.isEmpty()) {
          a = null;
        }
        if (nextB.isEmpty()) {
          b = null;
        }
      }
    }

    double actualPathCost = actual.edgeSet().stream().mapToDouble(Edge::weight).sum();
    double expectedPathCost = expected.edgeSet().stream().mapToDouble(Edge::weight).sum();

    /* make sure the paths have the same cost */
    assertTrue(Math.abs(actualPathCost - expectedPathCost) < THRESHOLD);

    /* make sure a and b are null after traversal */
    assertNull(a);
    assertNull(b);
  }
}
