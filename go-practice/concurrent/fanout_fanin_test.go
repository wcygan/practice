package pipelines

import (
	"fmt"
	"github.com/stretchr/testify/require"
	"runtime"
	"testing"
)

// TestFanOutFanIn tests the fan out / fan in concurrency pattern (https://go.dev/blog/pipelines#fan-out-fan-in)
// WARNING: this makes no guarantee on ordering, so we use a set to verify the answer
func TestFanOutFanIn(t *testing.T) {
	given := []int{1, 2, 3, 4, 5, 6, 7}
	expected := make(map[int]bool)
	for _, n := range given {
		expected[n*2] = true
	}

	// create a stream of inputs
	done := make(chan interface{})
	inputStream := Values(done, given...)

	// "fan out" the pipeline
	numWorkers := runtime.NumCPU()
	multipliers := make([]<-chan int, numWorkers)
	for i := 0; i < numWorkers; i++ {
		multipliers[i] = Multiply(done, inputStream, 2)
	}

	// "fan in" the pipeline
	for num := range CombineIntStreams(done, multipliers...) {
		_, ok := expected[num]
		require.True(t, ok, fmt.Sprintf("failed for %d", num))
	}
}
