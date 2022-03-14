# Garbage Collection

The essence of Java's garbage collection is that rather than requiring the programmer to understand the precise lifetime
of every object in the system, the runtime should keep track of objects on the programmer's behalf and automatically get
rid of objects that are no longer required. The automatically reclaimed memory can then be wiped and reused.

There are two fundamental rules of garbage collection that all implementations are subject to:

1. Algorithms must collect all garbage
2. No live object must ever be collected

## Garbage Collection Glossary

#### Stop-the-world (STW)

The GC cycle requires all application threads to be paused while garbage is collected. This prevents application code
from invalidating the GC thread's view of the state of the heap. This is the usual case for most simple GC algorithms.

#### Concurrent

GC threads can run while application threads are running. This is difficult to achieve and expensive in terms of
computation needed. Virtually no algorithms are truly concurrent. Instead, complex tricks are used to give most of the
benefits of concurrent collection.

#### Parallel

Multiple threads are used to execute garbage collection

#### Exact

An exact GC scheme has enough type information about the state of the heap to ensure that all garbage can be collected
on a single cycle. More loosely, an exact scheme has the property that it can always tell the difference between
an `int` and a pointer.

#### Conservative

A conservative scheme lacks information of an exact scheme. As a result, conservative schemes frequently fritter away
resources and are typically far less efficient due to their fundamental ignorance of the type system they purport to
represent.

#### Moving

In a moving collector, objects can be relocated in memory. This means that they do not have stable addresses.
Environments that provide raw pointers (e.g., C++) are not a natural fit for moving collectors.

#### Compacting

At the end of the collection cycle, allocated memory (i.e., surviving objects) is arranged as a single contiguous memory
region (usually at the start of the region), and there is a pointer indicating the start of empty space that is
available for objects to be written into. A compacting collector will avoid memory fragmentation.

#### Evacuating

At the end of the collection cycle, the collected region is totally empty, and all live objects have been moved (
evacuated) to another region of memory.

## Introducing the HotSpot Runtime

HotSpot introduces terms that are more specific to the GC implementation.

As a reminder, Java has only two sorts of values:

1. Primitive Types (`byte`, `int`, etc.)
2. Object references

Many Java programmers talk loosely about *objects*, but for our purposes it is important to remember that, unlike C++,
Java has no general address dereference mechanism and can only use an *offset operator* (the `.` operator) to access
fields and call methods on *object references*. Also keep in mind that Java's method call semantics are purely
call-by-value, although for object references this means that the value that is copied is the address of the object in
the heap.

### Representing Objects at Runtime

todo @wcygan