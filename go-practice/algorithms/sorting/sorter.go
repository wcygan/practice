package sorting

type Sorter interface {
	Sorted(arr []int) []int
	Kind() string
}

func CopyOf(given []int) []int {
	return append([]int(nil), given...)
}
