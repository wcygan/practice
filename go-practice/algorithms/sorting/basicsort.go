package sorting

import "sort"

type BasicSort struct{}

func (b BasicSort) Sorted(arr []int) []int {
	clone := Clone(arr)
	sort.Ints(clone)
	return clone
}

func (b BasicSort) Kind() string {
	return "BasicSort"
}
