package sorting

import "sort"

type DefaultSort struct{}

func (d DefaultSort) Kind() string {
	return "DefaultSort"
}

func (d DefaultSort) Sorted(arr []int) []int {
	clone := Clone(arr)
	sort.Ints(clone)
	return clone
}
