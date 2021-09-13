# Notes from [Java Concurrency in Practice](https://jcip.net/)

### Summary of Part 1 (Chapters 2 - 5)

- All concurrency issues boil down to coordinating access to mutable state. The less mutable state, the easier it is to
  ensure thread safety.
- Make fields final unless they need to be mutable
- Immutable objects are automatically thread-safe
    - Immutable objects are simpler, safer, and can be shared freely without locking or defensive copying
- Encapsulation makes it practical to manage complexity
    - Encapsulating synchronization within objects makes it easier to preserve their invariants & easier to comply with
      their synchronization policy
- A program that accesses a mutable variable from multiple threads without synchronization is a broken program

### Notes:

#### From the introduction of Chapter 12: **Testing Concurrent Programs**

Most tests of concurrent classes fall into one or both of the classic categories of safety and liveness:

- "Nothing bad ever happens"
- "Something good eventually happens".

Tests of safety, which verify that a class's behavior conforms to its specification, usually take the form of testing
invariants.

Liveness properties present their own testing challenges. Liveness tests include tests of progress and non-progress,
which are hard to quantify.

Related to liveness are performance tests. Performance can be measured in a number of ways, including:

- **Throughput**: the rate at which a set of concurrent tasks is completed
- **Responsiveness**: the delay between a request for and completion of some action
- **Scalability**: the improvement in throughput (or lack thereof) as more resources are made available.

Aside: [XORShift](https://en.wikipedia.org/wiki/Xorshift), a cheap, okay-ish pseudo-random number function.

```java
class Random {
  static long xorShift(long x) {
    x ^= (x << 21);
    x ^= (x >>> 35);
    x ^= (x << 4);
    return x;
  }
}
```

**Summary of Chapter 12**:  
Testing concurrent programs for correctness can be extremely challenging because many of the possible failure modes of
concurrent programs are low-probability events that are sensitive to timing, load, and other hard-to-reproduce
conditions. Further, the testing infrastructure can introduce additional synchronization or timing constraints that can
mask concurrency problems in the code being tested.

Testing concurrent programs for performance can be equally challenging; Java programs are most difficult to test than
programs written in statically compiled languages like C, because timing measurements can be affected by dynamic
compilation, garbage collection, and adaptive optimization.