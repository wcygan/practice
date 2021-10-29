package graphs

import (
	"github.com/gyuho/goraph"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestBFS(t *testing.T) {
	g := basicGraph()

	res := goraph.BFS(g, A)

	for idx, node := range res {
		assert.Equal(t, node.String(), nodes[idx].String())
	}
}

const (
	A = goraph.StringID("A")
	B = goraph.StringID("B")
	C = goraph.StringID("C")
	D = goraph.StringID("D")
	E = goraph.StringID("E")
	F = goraph.StringID("F")
	G = goraph.StringID("G")
)

var nodes = []goraph.StringID{A, B, C, D, E, F, G}

// basicGraph returns a graph of (a -> b -> c -> d -> e -> f -> g)
func basicGraph() goraph.Graph {
	g := goraph.NewGraph()

	// Add the vertices
	g.AddNode(goraph.NewNode(A.String()))
	g.AddNode(goraph.NewNode(B.String()))
	g.AddNode(goraph.NewNode(C.String()))
	g.AddNode(goraph.NewNode(D.String()))
	g.AddNode(goraph.NewNode(E.String()))
	g.AddNode(goraph.NewNode(F.String()))
	g.AddNode(goraph.NewNode(G.String()))

	// Add the edges
	g.AddEdge(A, B, 1)
	g.AddEdge(B, C, 1)
	g.AddEdge(C, D, 1)
	g.AddEdge(D, E, 1)
	g.AddEdge(E, F, 1)
	g.AddEdge(F, G, 1)

	return g
}