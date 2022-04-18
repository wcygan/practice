# Scala Practice

## Installations

Install [Ammonite](http://ammonite.io/) [Mill](https://com-lihaoyi.github.io/mill/mill/Intro_to_Mill.html):

```bash
$ brew install ammonite-repl
$ brew install mill
```

## Generate IntelliJ IDEA project files

```bash
$ mill mill.scalalib.GenIdea/idea
```

## Running the tests

```
$ mill lib.test
```

## Examples

Show below are some of the examples you can find in this repository:

### Data Structures

#### Trees

- [Trie](lib/src/io/wcygan/collections/tree/Trie.scala)

### Algorithms

#### Sorting

- [MergeSort](lib/src/io/wcygan/algorithms/sort/MergeSort.scala)
- [Binary Search](lib/src/io/wcygan/algorithms/search/BinarySearch.scala)
- [Breadth-First Search](lib/src/io/wcygan/algorithms/search/BreadthFirstSearch.scala)

## Ivy Dependency Issues

Sometimes the ivy dependencies won't automatically be indexed for a scala script for Ammonite

I can sometimes fix this by running `mill mill.scalalib.GenIdea/idea` and then also I can
also click on the highlighted error and choose `create library from jar...`
-> `Fix all 'Ammonite unresolved import' problems in file`

## Directory Tree

You will find the source code in the [lib/src/io/wcygan](lib/src/io/wcygan) folder.

```
.
├── build.sc
├── lib
│   ├── src
│   │   └── io
│   │       └── wcygan
│   │           ├── algorithms
│   │           │   ├── search
│   │           │   │   ├── BinarySearch.scala
│   │           │   │   ├── BreadthFirstSearch.scala
│   │           │   │   └── ShortestPath.scala
│   │           │   └── sort
│   │           │       ├── HeapSort.scala
│   │           │       ├── MergeSort.scala
│   │           │       └── Sorter.scala
│   │           └── collections
│   │               └── tree
│   │                   ├── ImmutableTrie.scala
│   │                   └── Trie.scala
│   └── test
│       └── src
│           └── io
│               └── wcygan
│                   ├── algorithms
│                   │   ├── search
│                   │   │   ├── BinarySearchTest.scala
│                   │   │   ├── BreadthFirstSearchTest.scala
│                   │   │   └── ShortestPathTest.scala
│                   │   └── sort
│                   │       └── SorterTest.scala
│                   └── collections
│                       └── tree
│                           ├── ImmutableTrieTest.scala
│                           └── TrieTest.scala
├── mill
├── readme.md
└── scripts
    ├── Hello.sc
    ├── NLargestFiles.sc
    └── scripts.md


```