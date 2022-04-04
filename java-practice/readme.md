# Practice

Practicing Data Structures, Algorithms, Concurrency, and more in [Java](https://www.oracle.com/java/technologies/)!

## Table of Contents

- **[Cool Stuff](#cool-stuff)**<br>
  - **[Data Structures](#data-structures)**<br>
  - **[Algorithms](#algorithms)**<br>
- **[Build and Run](#build-and-run)**<br>
- **[Property-Based Testing with JUnit-Quickcheck](#property-based-testing-with-junit-quickcheck)**<br>
- **[References](#references)**<br>
- **[Contributing](#contributing)**<br>

## Cool Stuff

### Data Structures

1. [LRU Cache](src/main/java/io/wcygan/collections/cache/LRUCache.java)
2. [Red Black Tree](src/main/java/io/wcygan/collections/tree/LLRedBlackTree.java)
3. [Disjoint Set](src/main/java/io/wcygan/collections/set/DisjointSet.java)
4. [D-Way Heap](src/main/java/io/wcygan/collections/queue/DWayHeap.java)
5. [Graph](src/main/java/io/wcygan/collections/graph/SimpleGraph.java)
6. [Non-blocking Queue](src/main/java/io/wcygan/concurrent/nonblocking/NonblockingQueue.java)
7. [Non-blocking Stack](src/main/java/io/wcygan/concurrent/nonblocking/NonblockingStack.java)
8. [Thread-ID based Lock](src/main/java/io/wcygan/concurrent/locks/TIDLock.java)

### Algorithms

1. [Quick Sort](src/main/java/io/wcygan/algorithms/sorting/QuickSort.java)
2. [Merge Sort](src/main/java/io/wcygan/algorithms/sorting/MergeSort.java)
3. [Parallel Merge Sort](src/main/java/io/wcygan/algorithms/sorting/ParallelMergeSort.java)
4. [Shortest Path](src/main/java/io/wcygan/algorithms/graph/pathfinding/ShortestPath.java) (Graph)
5. [Breadth-First Search](src/main/java/io/wcygan/algorithms/graph/traversal/BreadthFirstSearch.java) (Graph)
6. [Depth-First Search](src/main/java/io/wcygan/algorithms/graph/traversal/DepthFirstSearch.java) (Graph)

## Benchmarking

Using [jmh](https://github.com/openjdk/jmh) you are able to benchmark java code.

To execute the benchmarks in `src/jmh`, run the following command:

```
$ gradle jmh
```

For example, you can see this in action in 
[CacheLinesBenchmark.java](src/jmh/java/io/wcygan/algorithms/gotchas/CacheLinesBenchmark.java) where we obtain
the following results:

```
(Run on 2021 M1 Max Macbook Pro)
Benchmark                                          Mode  Cnt  Score   Error  Units
i.w.a.gotchas.CacheLinesBenchmark.touchEveryItem     ss  100  0.171 ± 0.004  ms/op
i.w.a.gotchas.CacheLinesBenchmark.touchEveryLine     ss  100  0.143 ± 0.006  ms/op
```

Another example is [SortingBenchmark.java](src/jmh/java/io/wcygan/algorithms/gotchas/CacheLinesBenchmark.java) where we 
compare the benchmark running time of different sorting algorithms:

```
 Benchmark                                            Mode  Cnt   Score   Error  Units
 i.w.a.sorting.SortingBenchmark.classicSort             ss  100  11.862 ± 0.078  ms/op
 i.w.a.sorting.SortingBenchmark.heapsort                ss  100  13.471 ± 0.053  ms/op
 i.w.a.sorting.SortingBenchmark.mergesort               ss  100  16.587 ± 0.115  ms/op
 i.w.a.sorting.SortingBenchmark.parallelStandardSort    ss  100   2.506 ± 0.086  ms/op
 i.w.a.sorting.SortingBenchmark.quicksort               ss  100  12.454 ± 0.184  ms/op
 i.w.a.sorting.SortingBenchmark.standardSort            ss  100  13.430 ± 0.053  ms/op
```

## Build and Run

This project uses [Gradle](https://gradle.org/install/). Make sure that you
have [Java](https://java.com/en/download/help/download_options.html) installed.

#### Make sure you're executing these commands in the folder `Practice/java-practice`.

To run the entire suite of tests:

```
$ ./gradlew test
```

To run a specific test class:

```
$ ./gradlew test --tests <TestClassName>
```

For example, I can run the tests
at [WeightedShortestPathTest](https://github.com/wcygan/Practice/blob/master/src/test/java/io/wcygan/algorithms/graph/pathfinding/WeightedShortestPathTest.java)
with:

```
$ ./gradlew test --tests WeightedShortestPathTest
```

Further, we can a **single** test by specifying its fully qualified path like so:

```
$ ./gradlew test --tests io.wcygan.algorithms.graph.pathfinding.WeightedShortestPathTest.testWeightedShortestPath
```

Running any type of test should generate a [JaCoCo](https://github.com/jacoco/jacoco) report file
at `build/jacocoHtml/index.html`
which indicates various types of
[Program Coverage properties](https://www.eclemma.org/userdoc/coverageproperties.html) for the execution of the test
your ran.

### Building a jar file

Builds a jar containing **all dependencies** of the project

```
$ ./gradlew shadowJar
```

Builds a jar containing **only the application classes** from the project

```
$ ./gradlew jar
```

## Property-Based Testing with JUnit-Quickcheck

Property-Based Testing allows you to test the programs you write by feeding a program randomly generated inputs.
See [Getting Started with JUnit-Quickcheck](https://pholser.github.io/junit-quickcheck/site/1.0/usage/getting-started.html)
for more details.

Once you've written a property-based test (like
[SortingQuickCheck](https://github.com/wcygan/Practice/blob/master/src/test/java/io/wcygan/algorithms/sorting/SortingQuickCheck.java))
you can execute it in isolation just as we did before:

```
$ ./gradlew test --tests io.wcygan.algorithms.sorting.SortingQuickCheck.testSortingAlgorithms
```

Additionally, this will generate a [JaCoCo](https://github.com/jacoco/jacoco) report file which represents the coverage
observed during the execution of the property-based test.

You can configure the execution of your property-based test using the elements of
the [@Property annotation](https://pholser.github.io/junit-quickcheck/site/1.0/junit-quickcheck-core/apidocs/com/pholser/junit/quickcheck/Property.html)
. For example, you can write `@Property(trials = 10)` to have the test execute 10 times.

## References

I'm using the following material as a reference:

1. [Introduction to Algorithms](https://mitpress.mit.edu/books/introduction-algorithms-third-edition)
2. [Java Concurrency in Practice](https://jcip.net/)
3. [The Art of Multiprocessor Programming](https://www.oreilly.com/library/view/the-art-of/9780123705914/)
4. [Effective Java](https://www.oreilly.com/library/view/effective-java/9780134686097/)

## Contributing

1. **Fork** the repo on GitHub
2. **Clone** the project to your own machine
3. **Commit** changes to your own branch
4. **Push** your work back up to your fork
5. Submit a **Pull request** for your work

Further, I recommend you use [IntelliJ](https://www.jetbrains.com/idea/) with the
[google-java-format](https://plugins.jetbrains.com/plugin/8527-google-java-format)
plugin to format the code you submit.