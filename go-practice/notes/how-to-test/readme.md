# Testing

The `go test` subcommand is a test driver for Go packages that are organized according to certain conventions.

Within `*_test.go` files, three kinds of functions are treated specially:

1. A [**test**](https://pkg.go.dev/testing#pkg-overview) function, which is a function whose name begins with `Test`,
   exercises some program logic for correct behavior. `go test` calls the test function and reports the result, which is
   either PASS or FAIL.
2. A [**benchmark**](https://pkg.go.dev/testing#hdr-Benchmarks) function has a name beginning with `Benchmark` and
   measures the performance of some operation; `go test` reports the mean execution time of the operation.
3. Lastly, an [**example**](https://pkg.go.dev/testing#hdr-Examples) function, whose name starts with `Example`,
   provides machine-checked documentation.

## Useful Testing Techniques

### Sub-tests

[**Sub-tests**](https://pkg.go.dev/testing#hdr-Subtests_and_Sub_benchmarks) are a convenient way of testing. You can
share setup & teardown code, test various states of execution, and combine similar tests into one scope.

```go
func TestFoo(t *testing.T) {
// <setup code>
t.Run("A=1", func (t *testing.T) { ... })
t.Run("A=2", func (t *testing.T) { ... })
t.Run("B=1", func (t *testing.T) { ... })
// <tear-down code>
}
```

### Property based testing with [**quick**](https://pkg.go.dev/testing/quick)

Quick provides a `check` utility that accomplishes the following:

Check looks for an input to f, any function that returns bool, such that f returns false. It calls f repeatedly, with
arbitrary values for each argument. If f returns false on a given input, Check returns that input as a *CheckError. For
example:

```go
func TestOddMultipleOfThree(t *testing.T) {
f := func (x int) bool {
y := OddMultipleOfThree(x)
return y%2 == 1 && y%3 == 0
}
if err := quick.Check(f, nil); err != nil {
t.Error(err)
}
}
```

Further reading:

- [First Class Fuzzing in Go](https://go.googlesource.com/proposal/+/master/design/draft-fuzzing.md)
- [Fuzzing is Beta Ready](https://go.dev/blog/fuzz-beta)

## Profiling

When we wish to look carefully at the speed of our programs, the best technique for identifying the critical code is
*profiling*. Profiling is an automated approach to performance measurement based on sampling a number of profile
*events* during execution, then extrapolating from them during a post-processing step; the resulting statistical summary
is called a *profile*.

Further reading:

- [Profiling Go Programs](https://go.dev/blog/pprof)
- [Diagnostics: Profiling](https://golang.org/doc/diagnostics#profiling)