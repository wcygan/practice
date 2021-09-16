package sorting

import "sort"

type BasicSort struct {
}

func (b BasicSort) Sorted(arr []int) []int {
	new := CopyOf(arr)
	sort.Ints(new)
	return new
}

func (b BasicSort) Kind() string {
	return "BasicSort"
}
