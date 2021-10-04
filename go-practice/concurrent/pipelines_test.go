package pipelines

import (
	"fmt"
	"github.com/stretchr/testify/require"
	"testing"
)

// TestAddMultiply asserts that the pipeline adds one to every integer then multiplies them by two.
//				   These operations take place concurrently.
func TestAddMultiply(t *testing.T) {
	given := []int{1, 2, 3, 4}
	expected := []int{4, 6, 8, 10}

	done := make(chan interface{})
	defer close(done)

	// Add 1 then Multiply by 2
	values := Values(done, given...)
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

// TestTakeFromRepeatedStream tests that values are properly retrieved from a repeated stream
func TestTakeFromRepeatedStream(t *testing.T) {
	repeat := 3
	given := []interface{}{1, 2}
	var expected []interface{}
	for i := 0; i < repeat; i++ {
		expected = append(expected, given...)
	}

	done := make(chan interface{})
	defer close(done)

	n := repeat * len(given)
	pipeline := Take(done, Repeat(done, given...), n)

	for i := 0; i < n; i++ {
		want := given[i%len(given)]
		got := <-pipeline
		require.Equal(t, want, got)
	}
}

func TestTee(t *testing.T) {
	done := make(chan interface{})
	defer close(done)

	out1, out2 := Tee(done, Take(done, Repeat(done, 1, 2), 4))

	for val := range out1 {
		require.Equal(t, val, <-out2)
	}
}

func TestBridge(t *testing.T) {
	max := 5
	expected := make(map[interface{}]bool)
	for i := 0; i < max; i++ {
		expected[i] = true
	}

	done := make(chan interface{})
	defer close(done)

	values := func() chan (<-chan interface{}) {
		channelStream := make(chan (<-chan interface{}))
		go func() {
			defer close(channelStream)
			for i := 0; i < max; i++ {
				stream := make(chan interface{}, 1)
				stream <- i
				close(stream)
				channelStream <- stream
			}
		}()
		return channelStream
	}

	for val := range Bridge(nil, values()) {
		_, ok := expected[val]
		require.Truef(t, ok, fmt.Sprintf("failed for %d", val))
	}
}
