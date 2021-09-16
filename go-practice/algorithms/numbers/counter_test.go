package numbers

import (
	"testing"
)

func TestCounter(t *testing.T) {

	t.Run("initial count", func(t *testing.T) {
		counter := Counter{}
		assertCount(t, counter, 0)
	})

	t.Run("increment", func(t *testing.T) {
		counter := Counter{}
		counter.Increment()
		assertCount(t, counter, 1)
	})

	t.Run("increment 5 times", func(t *testing.T) {
		counter := Counter{}

		five := 5

		for i := 0; i < five; i++ {
			counter.Increment()
		}

		assertCount(t, counter, five)
	})
}

func assertCount(t testing.TB, counter Counter, want int) {
	t.Helper()
	got := counter.Count()

	if got != want {
		t.Errorf("got %d want %d", got, want)
	}
}
