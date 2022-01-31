package io.wcygan.collections.graph;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleGraphTest {

    @Test
    public void happyPath() {
        SimpleGraph<Integer> graph = new SimpleGraph<>();

        /* add vertices */
        Vertex<Integer> v1 = graph.addVertex(1);
        Vertex<Integer> v2 = graph.addVertex(2);
        Vertex<Integer> v3 = graph.addVertex(3);

        /* add edges */
        Edge<Integer> e1 = graph.addEdge(v1, v2);
        Edge<Integer> e2 = graph.addEdge(v1, v3);

        /* verify graph properties */
        assertEquals(3, graph.vertexSet().size());
        assertEquals(2, graph.edgeSet().size());
        assertTrue(graph.containsVertex(v1));
        assertTrue(graph.containsVertex(v2));
        assertTrue(graph.containsVertex(v3));
        assertTrue(graph.containsEdge(e1));
        assertTrue(graph.containsEdge(e2));
        assertEquals(e1.source(), v1);
        assertEquals(e1.target(), v2);
        assertEquals(e2.source(), v1);
        assertEquals(e2.target(), v3);
        assertEquals(Set.of(e1), graph.incomingEdgesOf(v2));
        assertEquals(Set.of(e2), graph.incomingEdgesOf(v3));
        assertEquals(Set.of(e1, e2), graph.outgoingEdgesOf(v1));
        assertEquals(Set.of(), graph.outgoingEdgesOf(v2));

        /* these shouldn't be in the graph! */
        Vertex<Integer> badVertex1 = new Vertex<>(1, "Foo!");
        Vertex<Integer> badVertex2 = new Vertex<>(2, "Bar!");
        Edge<Integer> badEdge1 = new Edge<>(badVertex1, badVertex2, 100.0);
        assertFalse(graph.containsEdge(badEdge1));
        assertFalse(graph.containsVertex(badVertex1));
        assertFalse(graph.containsVertex(badVertex2));
        assertFalse(graph.containsEdge(v1, badVertex1));
    }

    @Test
    public void edgeSetDoesNotClearInner() {
        SimpleGraph<Integer> graph = new SimpleGraph<>();

        /* add vertices */
        Vertex<Integer> v1 = graph.addVertex(1);
        Vertex<Integer> v2 = graph.addVertex(2);
        Vertex<Integer> v3 = graph.addVertex(3);

        /* add edges */
        graph.addEdge(v1, v2);
        graph.addEdge(v1, v3);

        /* clear the set returned to the client */
        Set<Edge<Integer>> edges = graph.edgeSet();
        edges.clear();

        /* the graph should still have its edges */
        assertEquals(2, graph.edgeSet().size());
    }
}
