# Scala Practice

## Examples

Show below are some of the examples you can find in this repository:

### Data Structures

#### Trees

- [Trie](app/src/main/scala/io/wcygan/collections/tree/Trie.scala)

### Algorithms

#### Sorting
- [MergeSort](app/src/main/scala/io/wcygan/algorithms/sort/MergeSort.scala)

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
│       │               │   └── sort
│       │               │       ├── HeapSort.scala
│       │               │       ├── MergeSort.scala
│       │               │       └── Sorter.scala
│       │               └── collections
│       │                   └── tree
│       │                       └── Trie.scala
│       └── test
│           ├── resources
│           └── scala
│               └── io
│                   └── wcygan
│                       ├── AppTest.scala
│                       ├── algorithms
│                       │   └── sort
│                       │       └── SorterTest.scala
│                       └── collections
│                           └── tree
│                               └── TrieSuite.scala
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