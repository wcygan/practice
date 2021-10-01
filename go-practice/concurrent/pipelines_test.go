package pipelines

import (
	"github.com/stretchr/testify/require"
	"testing"
)

// TestAddMultiply asserts that the pipeline adds one to every integer then multiplies them by two.
//				   These operations take place concurrently.
func TestAddMultiply(t *testing.T) {
	expected := []int{4, 6, 8, 10}

	done := make(chan interface{})
	defer close(done)

	// Add 1 then Multiply by 2
	values := Values(done, 1, 2, 3, 4)
	pipeline := Multiply(done, Add(done, values, 1), 2)

	for _, want := range expected {
		got := <-pipeline
		require.Equal(t, want, got)
	}
}

// TestValues asserts the identity property of the given Values
func TestValues(t *testing.T) {
	expected := []int{1, 2, 3, 4}

	done := make(chan interface{})
	defer close(done)
	values := Values(done, expected...)

	for _, want := range expected {
		got := <-values
		require.Equal(t, want, got)
	}
}
