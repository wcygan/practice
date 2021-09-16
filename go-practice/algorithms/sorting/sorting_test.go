package sorting

import (
	"reflect"
	"testing"
	"testing/quick"
)

func TestSortingAlgorithms(t *testing.T) {
	sorters := []Sorter{
		BasicSort{},
	}

	sortsAreValid := func(given []int) bool {
		want := BasicSort{}.Sorted(given)

		for _, sorter := range sorters {
			got := sorter.Sorted(CopyOf(given))
			assertEquals(sorter.Kind(), got, want, t)
		}

		return true
	}

	if err := quick.Check(sortsAreValid, &quick.Config{MaxCount: 1000}); err != nil {
		t.Error(err)
	}

}

func assertEquals(kind string, got, want []int, t *testing.T) {
	if !reflect.DeepEqual(got, want) {
		t.Fatalf("Sorting failed for %s", kind)
	}
}
