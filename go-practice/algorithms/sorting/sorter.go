package sorting

type Sorter interface {
	// Sorted returns a sorted clone of the given array
	Sorted(given []int) []int
	// Kind returns the name of the sorter
	Kind() string
}
