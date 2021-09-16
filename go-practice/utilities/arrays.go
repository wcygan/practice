package utilities

// Clone creates a deeply-equal clone of the given array
func Clone(given []int) []int {
	return append([]int(nil), given...)
}