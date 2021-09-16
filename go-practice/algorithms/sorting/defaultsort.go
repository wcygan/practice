package sorting

import (
	"practice/utilities"
	"sort"
)

type DefaultSort struct{}

func (d DefaultSort) Kind() string {
	return "DefaultSort"
}

func (d DefaultSort) Sorted(arr []int) []int {
	clone := utilities.Clone(arr)
	sort.Ints(clone)
	return clone
}
