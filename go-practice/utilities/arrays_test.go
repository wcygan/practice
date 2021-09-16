package utilities

import (
	"reflect"
	"testing"
)

func TestClone(t *testing.T) {
	t.Run("Two arrays are equal", func(t *testing.T) {
		want := []int{1,2,3}
		got := Clone(want)

		if !reflect.DeepEqual(got, want) {
			t.Errorf("The given arrays aren't deeply equal!")
		}
	})
}