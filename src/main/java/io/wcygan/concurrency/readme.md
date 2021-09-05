# Notes from [Java Concurrency in Practice](https://jcip.net/)

## Summary of Part 1

- All concurrency issues boil down to coordinating access to mutable state. The less mutable state, the easier it is to
  ensure thread safety.
- Make fields final unless they need to be mutable
- Immutable objects are automatically thread-safe
    - Immutable objects are simpler, safer, and can be shared freely without locking or defensive copying
- Encapsulation makes it practical to manage complexity
    - Encapsulating synchronization within objects makes it easier to preserve their invariants & easier to comply with
      their synchronization policy
- A program that accesses a mutable variable from multiple threads without synchronization is a broken program