package sorting

import "practice/utilities"

type QuickSort struct{}

func (q QuickSort) Kind() string {
	return "QuickSort"
}

func (q QuickSort) Sorted(arr []int) []int {
	clone := utilities.Clone(arr)
	quickSort(clone, 0, len(arr)-1)
	return clone
}

func quickSort(arr []int, start, end int) {
	if start < end {
		mid := partition(arr, start, end)
		quickSort(arr, start, mid-1)
		quickSort(arr, mid+1, end)
	}
}

func partition(arr []int, start, end int) int {
	x := arr[end]
	i := start

	for j := start; j < end; j++ {
		if arr[j] <= x {
			swap(arr, i, j)
			i += 1
		}
	}

	swap(arr, i, end)
	return i
}

func swap(arr []int, fst, snd int) {
	arr[fst], arr[snd] = arr[snd], arr[fst]
}
