package sorting

import "practice/utilities"

type MergeSort struct{}

func (m MergeSort) Kind() string {
	return "MergeSort"
}

func (m MergeSort) Sorted(arr []int) []int {
	clone := utilities.Clone(arr)
	mergeSort(clone)
	return clone
}

func mergeSort(arr []int) {
	if len(arr) <= 1 {
		return
	}

	mid := len(arr) / 2
	left := utilities.Clone(arr[0:mid])
	right := utilities.Clone(arr[mid:])

	mergeSort(left)
	mergeSort(right)
	merge(arr, left, right)
}

func merge(arr, left, right []int) {
	i, j, k := 0, 0, 0

	for (i < len(left)) && (j < len(right)) {
		if left[i] < right[j] {
			arr[k] = left[i]
			i++
			k++
		} else {
			arr[k] = right[j]
			j++
			k++
		}
	}

	for i < len(left) {
		arr[k] = left[i]
		i++
		k++
	}

	for j < len(right) {
		arr[k] = right[j]
		j++
		k++
	}
}