package queue

import (
	"testing"
	"testing/quick"
)

func TestQueues(t *testing.T) {
	queues := []Queue{
		NewBoundedChannelQueue(),
	}

	queuesAreValid := func(given []int)  bool {

		// test each queue
		for _, queue := range queues {

			// push all items into queue
			for _, item := range given {
				queue.Push(item)
			}

			// pop all items from queue & assert equality
			for i := 0; i < len(given); i++ {
				want := given[i]
				got := queue.Pop()
				if want != got {
					t.Errorf("got %d want %d", got, want)
				}
			}
		}

		return true
	}

	if err := quick.Check(queuesAreValid, &quick.Config{MaxCount: 10000}); err != nil {
		t.Error(err)
	}
}