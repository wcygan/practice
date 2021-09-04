# Practice

Practicing Data Structures, Algorithms, Concurrency, and more!

## Build and Run

This project uses [Gradle](https://gradle.org/install/). Make sure that you
have [Java](https://java.com/en/download/help/download_options.html) installed.

To run the entire suite of tests:

```
$ ./gradlew test
```

To run a specific test class:

```
$ ./gradlew test --tests <TestClassName>
```

For example, I can run the tests
at [SimpleStackTest](https://github.com/wcygan/Practice/blob/459f16c3e3a942447f58fb5c70772a32d3a6efa9/src/test/java/io/wcygan/data_structures/stack/SimpleStackTest.java#L9)
with:

```
$ ./gradlew test --tests SimpleStackTest
```

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