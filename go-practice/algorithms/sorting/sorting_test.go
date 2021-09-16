package sorting

import (
	"math/rand"
	"reflect"
	"testing"
	"testing/quick"
)

func TestSortingAlgorithms(t *testing.T) {
	sorters := []Sorter{
		DefaultSort{},
		QuickSort{},
	}

	sortsAreValid := func(given []int) bool {
		want := DefaultSort{}.Sorted(given)

		for _, sorter := range sorters {
			rand.Shuffle(len(given), func(i, j int) { given[i], given[j] = given[j], given[i] })
			got := sorter.Sorted(given)
			assertEquals(sorter.Kind(), got, want, t)
		}

		return true
	}

	if err := quick.Check(sortsAreValid, &quick.Config{MaxCount: 10000}); err != nil {
		t.Error(err)
	}

}

func assertEquals(kind string, got, want []int, t *testing.T) {
	if !reflect.DeepEqual(got, want) {
		t.Fatalf("Sorting failed for %s", kind)
	}
}
