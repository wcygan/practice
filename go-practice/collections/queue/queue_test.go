package queue

import (
	"testing"
	"testing/quick"
)

func TestQueues(t *testing.T) {

	tests := []struct {
		name  string
		queue Queue
	}{
		{name: "Bounded Channel Queue", queue: NewBoundedChannelQueue()},
		{name: "Linked Queue", queue: NewLinkedQueue()},
	}

	// Property: all queues should behave the same way in a single-threaded environment (FIFO)
	queueProperty := func(given []int) bool {

		// test each queue
		for _, test := range tests {

			// push all items into queue
			for _, item := range given {
				test.queue.Push(item)
			}

			// pop all items from queue & assert equality
			for i := 0; i < len(given); i++ {
				want := given[i]
				got := test.queue.Pop()
				if want != got {
					t.Errorf("test: %s: got %d want %d", test.name, got, want)
				}
			}
		}

		return true
	}

	if err := quick.Check(queueProperty, &quick.Config{MaxCount: 1}); err != nil {
		t.Error(err)
	}
}
