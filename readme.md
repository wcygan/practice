# Practice

Practicing Data Structures, Algorithms, Concurrency, and more in [Java](https://www.oracle.com/java/technologies/)
, [Go](https://go.dev/), and [Rust](https://www.rust-lang.org/)!

<p float="left">
  <img src="https://1000logos.net/wp-content/uploads/2020/09/Java-Logo-500x313.png" width="100" /> 
  <img src="https://download.logo.wine/logo/Go_(programming_language)/Go_(programming_language)-Logo.wine.png" width="100" />
  <img src="https://rustacean.net/assets/rustacean-flat-happy.png" width="100" />
</p>

## Table of Contents

- **[Repositories](#repositories)**<br>
- **[References](#references)**<br>
- **[Contributing](#contributing)**<br>

## Repositories

Find examples in these folders:

1. [Practice with Java](java-practice)
2. [Practice with Go](go-practice)
3. [Practice with Rust](rust-practice)

## References

### General

- [Introduction to Algorithms](https://mitpress.mit.edu/books/introduction-algorithms-third-edition)
- [The Art of Multiprocessor Programming](https://www.oreilly.com/library/view/the-art-of/9780123705914/)

### Java

- [Java Concurrency in Practice](https://jcip.net/)
- [Effective Java](https://www.oreilly.com/library/view/effective-java/9780134686097/)

### Go

- [Learn Go with Tests](https://quii.gitbook.io/learn-go-with-tests/)
- [The Go Programming Language](https://www.gopl.io/)
- [Concurrency in Go](https://www.oreilly.com/library/view/concurrency-in-go/9781491941294/)
- [Network Programming with Go](https://nostarch.com/networkprogrammingwithgo)
- [Distributed Services with Go](https://pragprog.com/titles/tjgo/distributed-services-with-go/)

### Rust

- [The Rust Programming Language](https://nostarch.com/Rust2018)
- [Rust in Action](https://www.manning.com/books/rust-in-action)
- [Rust for Rustaceans](https://nostarch.com/rust-rustaceans)
- [Creative Projects for Rust Programmers](https://www.oreilly.com/library/view/creative-projects-for/9781789346220/)

## Directory Tree

```
.
├── go-practice
│   ├── algorithms
│   │   ├── graphs
│   │   │   └── bfs_test.go
│   │   ├── numbers
│   │   │   ├── adder.go
│   │   │   ├── adder_test.go
│   │   │   ├── counter.go
│   │   │   └── counter_test.go
│   │   └── sorting
│   │       ├── defaultsort.go
│   │       ├── mergesort.go
│   │       ├── quicksort.go
│   │       ├── sorter.go
│   │       └── sorting_test.go
│   ├── collections
│   │   └── queue
│   │       ├── channel_queue.go
│   │       ├── linked_queue.go
│   │       ├── queue.go
│   │       └── queue_test.go
│   ├── concurrent
│   │   ├── fanout_fanin_test.go
│   │   ├── pipelines.go
│   │   └── pipelines_test.go
│   ├── go.mod
│   ├── go.sum
│   ├── Makefile
│   ├── notes
│   │   └── how-to-test
│   │       └── readme.md
│   ├── programs
│   │   ├── chat-server
│   │   │   └── chat.go
│   │   └── disk-usage
│   │       └── disk_usage.go
│   ├── readme.md
│   └── utilities
│       ├── arrays.go
│       └── arrays_test.go
├── java-practice
│   ├── build.gradle
│   ├── gradle
│   │   └── wrapper
│   │       ├── gradle-wrapper.jar
│   │       └── gradle-wrapper.properties
│   ├── gradlew
│   ├── gradlew.bat
│   ├── readme.md
│   ├── settings.gradle
│   └── src
│       ├── main
│       │   └── java
│       │       └── io
│       │           └── wcygan
│       │               ├── algorithms
│       │               │   ├── dynamic_programming
│       │               │   │   ├── readme.md
│       │               │   │   └── RodCutting.java
│       │               │   ├── graph
│       │               │   │   ├── pathfinding
│       │               │   │   │   └── ShortestPath.java
│       │               │   │   └── traversal
│       │               │   │       ├── BreadthFirstSearch.java
│       │               │   │       ├── DepthFirstSearch.java
│       │               │   │       └── Reachable.java
│       │               │   ├── miscellaneous
│       │               │   │   └── BinarySearch.java
│       │               │   ├── numbers
│       │               │   │   ├── Counter.java
│       │               │   │   ├── FibonacciSequence.java
│       │               │   │   └── PrimeFactorization.java
│       │               │   └── sorting
│       │               │       ├── HeapSort.java
│       │               │       ├── ParallelMergeSort.java
│       │               │       ├── QuickSort.java
│       │               │       ├── SequentialMergeSort.java
│       │               │       └── SortingAlgorithm.java
│       │               ├── App.java
│       │               ├── collections
│       │               │   ├── dictionary
│       │               │   │   ├── HashMap.java
│       │               │   │   ├── Map.java
│       │               │   │   └── TreeMap.java
│       │               │   ├── graph
│       │               │   │   ├── Color.java
│       │               │   │   ├── Edge.java
│       │               │   │   ├── Graph.java
│       │               │   │   ├── GraphBuilder.java
│       │               │   │   ├── SimpleGraph.java
│       │               │   │   └── Vertex.java
│       │               │   ├── list
│       │               │   │   ├── LinkedList.java
│       │               │   │   └── List.java
│       │               │   ├── queue
│       │               │   │   ├── Queue.java
│       │               │   │   └── SimpleUnboundedQueue.java
│       │               │   ├── set
│       │               │   │   ├── Set.java
│       │               │   │   └── TreeSet.java
│       │               │   └── stack
│       │               │       ├── SimpleStack.java
│       │               │       └── Stack.java
│       │               ├── common
│       │               │   ├── Bits.java
│       │               │   ├── Utilities.java
│       │               │   └── Waiter.java
│       │               ├── concurrent
│       │               │   ├── executors
│       │               │   │   └── task
│       │               │   │       └── SumTask.java
│       │               │   ├── locks
│       │               │   │   └── TTASLock.java
│       │               │   ├── nonblocking
│       │               │   │   ├── KoganPetrankQueue.java
│       │               │   │   ├── NonblockingCounter.java
│       │               │   │   ├── NonblockingQueue.java
│       │               │   │   ├── NonblockingRandom.java
│       │               │   │   └── NonblockingStack.java
│       │               │   └── readme.md
│       │               ├── gotchas
│       │               │   └── LazyOptional.java
│       │               └── modern
│       │                   ├── OptionalUtils.java
│       │                   ├── PredicateUtils.java
│       │                   ├── StreamUtils.java
│       │                   └── VarUtils.java
│       └── test
│           └── java
│               └── io
│                   └── wcygan
│                       ├── algorithms
│                       │   ├── dynamic_programming
│                       │   │   └── RodCuttingTest.java
│                       │   ├── graph
│                       │   │   ├── pathfinding
│                       │   │   │   ├── Helpers.java
│                       │   │   │   ├── UnweightedShortestPathTest.java
│                       │   │   │   └── WeightedShortestPathTest.java
│                       │   │   └── traversal
│                       │   │       └── GraphSearchTest.java
│                       │   ├── miscellaneous
│                       │   │   └── BinarySearchTest.java
│                       │   ├── numbers
│                       │   │   └── FibonacciSequenceTest.java
│                       │   └── sorting
│                       │       ├── IsSortedTest.java
│                       │       └── SortingQuickCheck.java
│                       ├── collections
│                       │   ├── dictionary
│                       │   │   └── DictionaryTest.java
│                       │   ├── graph
│                       │   │   └── SimpleGraphTest.java
│                       │   ├── list
│                       │   │   └── ListTest.java
│                       │   ├── queue
│                       │   │   └── SimpleUnboundedQueueTest.java
│                       │   ├── set
│                       │   │   └── TreeSetTest.java
│                       │   └── stack
│                       │       └── SimpleStackTest.java
│                       ├── common
│                       │   └── TestBits.java
│                       ├── concurrent
│                       │   ├── executors
│                       │   │   ├── ParSeqExecutorTest.java
│                       │   │   ├── ParSeqTestBase.java
│                       │   │   └── task
│                       │   │       └── SumTaskTest.java
│                       │   ├── locks
│                       │   │   └── LockTest.java
│                       │   ├── nonblocking
│                       │   │   ├── KoganPetrankQueueTest.java
│                       │   │   ├── NonblockingCounterTest.java
│                       │   │   ├── NonBlockingQueueTest.java
│                       │   │   ├── NonblockingRandomTest.java
│                       │   │   └── NonblockingStackTest.java
│                       │   └── speed
│                       │       └── CachedFibonacciSpeedTest.java
│                       ├── gotchas
│                       │   └── LazyOptionalTest.java
│                       └── modern
│                           ├── OptionalUtilsTest.java
│                           ├── PredicateUtilsTest.java
│                           ├── StreamUtilsTest.java
│                           └── VarUtilsTest.java
├── readme.md
└── rust-practice
    ├── Cargo.lock
    ├── Cargo.toml
    ├── readme.md
    └── src
        ├── collections
        │   ├── list
        │   │   ├── list.rs
        │   │   └── mod.rs
        │   └── mod.rs
        ├── concurrent
        │   ├── mod.rs
        │   └── thread_example.rs
        └── lib.rs
```
