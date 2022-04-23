# Practice

Practicing Data Structures, Algorithms, Concurrency, and more in [Java](https://www.oracle.com/java/technologies/)
, [Go](https://go.dev/), and [Rust](https://www.rust-lang.org/)!

<p float="left">
  <img src="https://1000logos.net/wp-content/uploads/2020/09/Java-Logo-500x313.png" width="100" /> 
  <img src="https://download.logo.wine/logo/Go_(programming_language)/Go_(programming_language)-Logo.wine.png" width="100" />
  <img src="https://rustacean.net/assets/rustacean-flat-happy.png" width="100" />
    <img src="https://www.scala-lang.org/resources/img/frontpage/scala-spiral.png" width="40" />
</p>

## Table of Contents

- **[Cool Stuff](#cool-stuff)**<br>
  - [Data Structures](#data-structures)
  - [Algorithms](#algorithms)
  - [Leetcode](java-practice/src/main/java/io/wcygan/questions/leetcode)
- **[Repositories](#repositories)**<br>
- **[References & Books](#references)**<br>
- **[Directory Tree](#directory-tree)**<br>

## Cool Stuff
There are quite a few cool things inside. They're mainly implemented in Java, but you might find something in Rust, Go, or Scala too. Here are a few cool examples:

### Data Structures

1. [LRU Cache](java-practice/src/main/java/io/wcygan/collections/cache/LRUCache.java)
2. [Red Black Tree](java-practice/src/main/java/io/wcygan/collections/tree/LLRedBlackTree.java)
3. [Disjoint Set](java-practice/src/main/java/io/wcygan/collections/set/DisjointSet.java)
4. [D-Way Heap](java-practice/src/main/java/io/wcygan/collections/queue/DWayHeap.java)
5. [Trie](scala-practice/lib/src/io/wcygan/collections/tree/Trie.scala)
6. [ArrayBlockingQueue](java-practice/src/main/java/io/wcygan/concurrent/collections/queue/ArrayBlockingQueue.java)
7. [Non-blocking Queue](java-practice/src/main/java/io/wcygan/concurrent/collections/queue/NonblockingQueue.java)
8. [Non-blocking Stack](java-practice/src/main/java/io/wcygan/concurrent/collections/stack/NonblockingStack.java)
9. [Thread-ID based Lock](java-practice/src/main/java/io/wcygan/concurrent/locks/TIDLock.java)
10. [Graph](java-practice/src/main/java/io/wcygan/collections/graph/SimpleGraph.java)

### Algorithms

1. [Functional QuickSort](scala-practice/lib/src/io/wcygan/algorithms/sort/QuickSort.scala)
2. [QuickSort](java-practice/src/main/java/io/wcygan/algorithms/sorting/QuickSort.java)
3. [MergeSort](java-practice/src/main/java/io/wcygan/algorithms/sorting/MergeSort.java)
4. [Parallel MergeSort](scala-practice/lib/src/io/wcygan/algorithms/sort/ParallelMergeSort.scala)
5. [Shortest Path](java-practice/src/main/java/io/wcygan/algorithms/graph/pathfinding/ShortestPath.java) (Graph)
6. [Breadth-First Search](java-practice/src/main/java/io/wcygan/algorithms/graph/traversal/BreadthFirstSearch.java) (Graph)
7. [Depth-First Search](java-practice/src/main/java/io/wcygan/algorithms/graph/traversal/DepthFirstSearch.java) (Graph)

## Repositories

Find examples in these folders:

1. [Practice with Java](java-practice)
2. [Practice with Go](go-practice)
3. [Practice with Rust](rust-practice)
4. [Practice with Scala](scala-practice)

## References

### General

- [The Algorithm Design Manual](https://www.algorist.com/)
- [Introduction to Algorithms](https://mitpress.mit.edu/books/introduction-algorithms-third-edition)
- [Guide to Competitive Programming](https://link.springer.com/book/10.1007/978-3-030-39357-1)
- [Advanced Algorithms and Data Structures](https://www.oreilly.com/library/view/advanced-algorithms-and/9781617295485VE/)
- [The Art of Multiprocessor Programming](https://www.oreilly.com/library/view/the-art-of/9780123705914/)
- [Modern Operating Systems](https://www.pearson.com/us/higher-education/program/Tanenbaum-Modern-Operating-Systems-4th-Edition/PGM80736.html)
- [Cracking the Coding Interview](https://www.crackingthecodinginterview.com/)

### Java

- [Java Concurrency in Practice](https://jcip.net/)
- [Effective Java](https://www.oreilly.com/library/view/effective-java/9780134686097/)
- [Java Coding Problems](https://www.packtpub.com/product/java-coding-problems/9781789801415)
- [Java Performance](https://www.oreilly.com/library/view/java-performance-2nd/9781492056102/)
- [Optimizing Java](https://www.oreilly.com/library/view/optimizing-java/9781492039259/)
- [Java: The Complete Reference](https://www.oreilly.com/library/view/java-the-complete/9781260463422/)

### Go

- [Learn Go with Tests](https://quii.gitbook.io/learn-go-with-tests/)
- [The Go Programming Language](https://www.gopl.io/)
- [Concurrency in Go](https://www.oreilly.com/library/view/concurrency-in-go/9781491941294/)
- [Powerful Command-Line Applications in Go](https://pragprog.com/titles/rggo/powerful-command-line-applications-in-go/)
- [Network Programming with Go](https://nostarch.com/networkprogrammingwithgo)
- [Distributed Services with Go](https://pragprog.com/titles/tjgo/distributed-services-with-go/)

### Rust

- [The Rust Programming Language](https://nostarch.com/Rust2018)
- [Rust in Action](https://www.manning.com/books/rust-in-action)
- [Rust for Rustaceans](https://nostarch.com/rust-rustaceans)
- [Creative Projects for Rust Programmers](https://www.oreilly.com/library/view/creative-projects-for/9781789346220/)
- [Command-Line Rust](https://www.oreilly.com/library/view/command-line-rust/9781098109424/)

### Scala
- [Hands on Scala](https://www.handsonscala.com/)
- [Programming in Scala](https://www.artima.com/shop/programming_in_scala_5ed)
- [Programming Scala](https://www.oreilly.com/library/view/programming-scala-3rd/9781492077886/)
- [Functional Programming Simplified](https://alvinalexander.com/scala/functional-programming-simplified-book/)
- [Modern Systems Programming with Scala Native](https://pragprog.com/titles/rwscala/modern-systems-programming-with-scala-native/)


## Directory Tree

```
.
├── go-practice
│   ├── Makefile
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
│   │   ├── list
│   │   │   ├── list.go
│   │   │   └── list_test.go
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
│       ├── jmh
│       │   └── java
│       │       └── io
│       │           └── wcygan
│       │               └── algorithms
│       │                   ├── gotchas
│       │                   │   └── CacheLinesBenchmark.java
│       │                   └── sorting
│       │                       └── SortingBenchmark.java
│       ├── main
│       │   └── java
│       │       └── io
│       │           └── wcygan
│       │               ├── App.java
│       │               ├── algorithms
│       │               │   ├── dynamic_programming
│       │               │   │   ├── RodCutting.java
│       │               │   │   └── readme.md
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
│       │               │   ├── sorting
│       │               │   │   ├── HeapSort.java
│       │               │   │   ├── MergeSort.java
│       │               │   │   ├── ParallelMergeSort.java
│       │               │   │   ├── QuickSort.java
│       │               │   │   └── SortingAlgorithm.java
│       │               │   └── strings
│       │               │       └── StringToInteger.java
│       │               ├── collections
│       │               │   ├── cache
│       │               │   │   ├── Cache.java
│       │               │   │   ├── LFUCache.java
│       │               │   │   └── LRUCache.java
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
│       │               │   │   ├── DoublyLinkedList.java
│       │               │   │   ├── LinkedList.java
│       │               │   │   └── List.java
│       │               │   ├── queue
│       │               │   │   ├── BinaryHeap.java
│       │               │   │   ├── DWayHeap.java
│       │               │   │   ├── Queue.java
│       │               │   │   └── SimpleUnboundedQueue.java
│       │               │   ├── set
│       │               │   │   ├── DisjointSet.java
│       │               │   │   ├── Set.java
│       │               │   │   └── TreeSet.java
│       │               │   ├── stack
│       │               │   │   ├── SimpleStack.java
│       │               │   │   └── Stack.java
│       │               │   └── tree
│       │               │       ├── BST.java
│       │               │       ├── LLRedBlackTree.java
│       │               │       ├── SearchTree.java
│       │               │       └── Trie.java
│       │               ├── common
│       │               │   ├── Bits.java
│       │               │   ├── Utilities.java
│       │               │   └── Waiter.java
│       │               ├── concurrent
│       │               │   ├── collections
│       │               │   │   ├── misc
│       │               │   │   │   ├── NonblockingCounter.java
│       │               │   │   │   └── NonblockingRandom.java
│       │               │   │   ├── queue
│       │               │   │   │   ├── ArrayBlockingQueue.java
│       │               │   │   │   ├── KoganPetrankQueue.java
│       │               │   │   │   └── NonblockingQueue.java
│       │               │   │   ├── set
│       │               │   │   │   ├── BaseHashSet.java
│       │               │   │   │   ├── BucketList.java
│       │               │   │   │   ├── LockFreeHashSet.java
│       │               │   │   │   ├── PhasedCuckooHashSet.java
│       │               │   │   │   ├── RefinableCuckooHashSet.java
│       │               │   │   │   └── StripedHashSet.java
│       │               │   │   └── stack
│       │               │   │       └── NonblockingStack.java
│       │               │   ├── examples
│       │               │   │   └── thread
│       │               │   │       ├── CreateThreadWithRunnable.java
│       │               │   │       ├── ExtendingThread.java
│       │               │   │       └── SingleProducerSingleConsumer.java
│       │               │   ├── executors
│       │               │   │   └── task
│       │               │   │       └── SumTask.java
│       │               │   ├── locks
│       │               │   │   ├── TIDLock.java
│       │               │   │   └── TTASLock.java
│       │               │   └── readme.md
│       │               ├── gotchas
│       │               │   └── LazyOptional.java
│       │               ├── modern
│       │               │   ├── OptionalUtils.java
│       │               │   ├── PredicateUtils.java
│       │               │   ├── StreamUtils.java
│       │               │   └── VarUtils.java
│       │               ├── notes
│       │               │   ├── gc.md
│       │               │   └── hardware_and_OS.md
│       │               ├── questions
│       │               │   └── leetcode
│       │               │       ├── ListNode.java
│       │               │       ├── TreeNode.java
│       │               │       ├── easy
│       │               │       │   ├── q21MergeTwoLists
│       │               │       │   │   └── Solution.java
│       │               │       │   └── q226InvertBinaryTree
│       │               │       │       └── Solution.java
│       │               │       ├── hard
│       │               │       │   ├── q23MergeKSortedLists
│       │               │       │   │   └── Solution.java
│       │               │       │   └── q432AllOne
│       │               │       │       └── AllOne.java
│       │               │       └── medium
│       │               │           ├── q1115FooBarConcurrent
│       │               │           │   └── FooBar.java
│       │               │           └── q148SortList
│       │               │               └── Solution.java
│       │               └── reflect
│       │                   └── Inspector.java
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
│                       │   ├── sorting
│                       │   │   ├── IsSortedTest.java
│                       │   │   └── SortingQuickCheck.java
│                       │   └── strings
│                       │       └── TestStringToInteger.java
│                       ├── collections
│                       │   ├── cache
│                       │   │   ├── LFUCacheTest.java
│                       │   │   └── LRUCacheTest.java
│                       │   ├── dictionary
│                       │   │   └── DictionaryTest.java
│                       │   ├── graph
│                       │   │   └── SimpleGraphTest.java
│                       │   ├── list
│                       │   │   ├── DoubleLinkedListTest.java
│                       │   │   └── LinkedListTest.java
│                       │   ├── queue
│                       │   │   ├── PriorityQueueTest.java
│                       │   │   └── SimpleUnboundedQueueTest.java
│                       │   ├── set
│                       │   │   ├── DisjointSetTest.java
│                       │   │   └── TreeSetTest.java
│                       │   ├── stack
│                       │   │   └── SimpleStackTest.java
│                       │   └── tree
│                       │       ├── BSTTest.java
│                       │       ├── RedBlackTreeTest.java
│                       │       ├── SearchTreeTest.java
│                       │       ├── TraversalTest.java
│                       │       └── TrieTest.java
│                       ├── common
│                       │   └── TestBits.java
│                       ├── concurrent
│                       │   ├── collections
│                       │   │   ├── ArrayBlockingQueueTest.java
│                       │   │   ├── KoganPetrankQueueTest.java
│                       │   │   ├── NonBlockingQueueTest.java
│                       │   │   ├── NonblockingCounterTest.java
│                       │   │   ├── NonblockingRandomTest.java
│                       │   │   └── NonblockingStackTest.java
│                       │   ├── executors
│                       │   │   ├── ParSeqExecutorTest.java
│                       │   │   ├── ParSeqTestBase.java
│                       │   │   └── task
│                       │   │       └── SumTaskTest.java
│                       │   ├── locks
│                       │   │   └── LockTest.java
│                       │   └── speed
│                       │       └── CachedFibonacciSpeedTest.java
│                       ├── gotchas
│                       │   ├── LazyOptionalTest.java
│                       │   └── TestStringPool.java
│                       ├── modern
│                       │   ├── OptionalUtilsTest.java
│                       │   ├── PredicateUtilsTest.java
│                       │   ├── StreamUtilsTest.java
│                       │   └── VarUtilsTest.java
│                       ├── questions
│                       │   └── leetcode
│                       │       ├── hard
│                       │       │   └── q432AllOne
│                       │       │       └── AllOneTest.java
│                       │       └── medium
│                       │           └── q148SortList
│                       │               └── SolutionTest.java
│                       ├── reflect
│                       │   └── InspectorTest.java
│                       └── testutils
│                           └── IntegerListGenerator.java
├── readme.md
├── rust-practice
│   ├── Cargo.lock
│   ├── Cargo.toml
│   ├── readme.md
│   └── src
│       ├── collections
│       │   ├── list
│       │   │   └── mod.rs
│       │   └── mod.rs
│       ├── concurrent
│       │   ├── mod.rs
│       │   └── thread_example.rs
│       └── lib.rs
└── scala-practice
    ├── build.sc
    ├── lib
    │   ├── src
    │   │   └── io
    │   │       └── wcygan
    │   │           ├── algorithms
    │   │           │   ├── search
    │   │           │   │   ├── BinarySearch.scala
    │   │           │   │   ├── BreadthFirstSearch.scala
    │   │           │   │   └── ShortestPath.scala
    │   │           │   └── sort
    │   │           │       ├── HeapSort.scala
    │   │           │       ├── MergeSort.scala
    │   │           │       ├── ParallelMergeSort.scala
    │   │           │       ├── QuickSort.scala
    │   │           │       └── Sorter.scala
    │   │           ├── collections
    │   │           │   └── tree
    │   │           │       ├── ImmutableTrie.scala
    │   │           │       └── Trie.scala
    │   │           └── examples
    │   │               └── concurrent
    │   │                   └── FuturesAndGlobalContext.scala
    │   └── test
    │       └── src
    │           └── io
    │               └── wcygan
    │                   ├── algorithms
    │                   │   ├── search
    │                   │   │   ├── BinarySearchTest.scala
    │                   │   │   ├── BreadthFirstSearchTest.scala
    │                   │   │   └── ShortestPathTest.scala
    │                   │   └── sort
    │                   │       └── SorterTest.scala
    │                   └── collections
    │                       └── tree
    │                           ├── ImmutableTrieTest.scala
    │                           └── TrieTest.scala
    ├── mill
    ├── readme.md
    └── scripts
        ├── Hello.sc
        ├── filesystem
        │   └── NLargestFiles.sc
        ├── scripts.md
        └── web-crawler
            └── Crawler.sc

```
