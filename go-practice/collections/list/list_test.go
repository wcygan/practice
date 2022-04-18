// Source: https://github.com/zyedidia/generic

package list

import (
	"fmt"
	"github.com/stretchr/testify/require"
	"testing"
)

func Example() {
	l := New[int]()
	l.PushBack(0)
	l.PushBack(1)
	l.PushBack(2)
	l.PushBack(3)

	l.Front.Each(func(i int) {
		fmt.Println(i)
	})
	// Output:
	// 0
	// 1
	// 2
	// 3
}

func TestAddRemove(t *testing.T) {
	l := New[int]()

	for i := 0; i < 10; i++ {
		l.PushBack(i)
	}

	require.Equal(t, 0, l.Front.Value)
	require.Equal(t, 9, l.Back.Value)
}
