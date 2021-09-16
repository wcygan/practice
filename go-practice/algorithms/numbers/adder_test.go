package numbers

import (
	"fmt"
	"testing"
	"testing/quick"
)

func TestAdder(t *testing.T) {
	// setup property based test
	comm := func(a, b int) bool {
		if Add(a, b) != (a + b) {
			return false
		}
		return true
	}

	// execute property based test
	if err := quick.Check(comm, nil); err != nil {
		t.Error(err)
	}
}

func ExampleAdd() {
	sum := Add(1, 5)
	fmt.Println(sum)
	// Output: 6
}
