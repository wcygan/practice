# Scala Practice

## Examples

Show below are some of the examples you can find in this repository:

### Data Structures

#### Trees

- [Trie](app/src/main/scala/io/wcygan/collections/tree/Trie.scala)

### Algorithms

#### Sorting
- [MergeSort](app/src/main/scala/io/wcygan/algorithms/sort/MergeSort.scala)
- [Binary Search](app/src/main/scala/io/wcygan/algorithms/search/BinarySearch.scala)
- [Breadth-First Search](app/src/main/scala/io/wcygan/algorithms/search/BreadthFirstSearch.scala)

## Directory Tree

You will find the source code in the [app/src/main/scala/io/wcygan](app/src/main/scala/io/wcygan) folder.

```
.
├── app
│   ├── build.gradle
│   └── src
│       ├── main
│       │   ├── resources
│       │   └── scala
│       │       └── io
│       │           └── wcygan
│       │               ├── App.scala
│       │               ├── algorithms
│       │               │   ├── search
│       │               │   │   ├── BinarySearch.scala
│       │               │   │   ├── BreadthFirstSearch.scala
│       │               │   │   └── ShortestPath.scala
│       │               │   └── sort
│       │               │       ├── HeapSort.scala
│       │               │       ├── MergeSort.scala
│       │               │       └── Sorter.scala
│       │               └── collections
│       │                   └── tree
│       │                       ├── ImmutableTrie.scala
│       │                       └── Trie.scala
│       └── test
│           ├── resources
│           └── scala
│               └── io
│                   └── wcygan
│                       ├── AppTest.scala
│                       ├── algorithms
│                       │   ├── search
│                       │   │   ├── BinarySearchTest.scala
│                       │   │   ├── BreadthFirstSearchTest.scala
│                       │   │   └── ShortestPathTest.scala
│                       │   └── sort
│                       │       └── SorterTest.scala
│                       └── collections
│                           └── tree
│                               ├── ImmutableTrieTest.scala
│                               └── TrieTest.scala
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── project
│   └── build.properties
├── readme.md
└── settings.gradle

```