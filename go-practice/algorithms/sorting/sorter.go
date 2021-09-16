package sorting

type Sorter interface {
	Sorted(arr []int) []int
	Kind() string
}

func Clone(given []int) []int {
	return append([]int(nil), given...)
}
