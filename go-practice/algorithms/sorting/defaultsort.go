package sorting

import "sort"

type DefaultSort struct{}

func (b DefaultSort) Kind() string {
	return "DefaultSort"
}

func (b DefaultSort) Sorted(arr []int) []int {
	clone := Clone(arr)
	sort.Ints(clone)
	return clone
}
