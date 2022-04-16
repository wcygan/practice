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

HotSpot represents Java objects at runtime via a structure called on `oop`. This is short for _ordinary object pointer_,
and is a genuine pointer in the C sense.

### GC Roots and Arenas

Articles and blog posts about HotSpot frequently refer to *GC roots*. These are "anchor points" for memory, essentially
known pointers that originate from outside a memory pool of interest and point into it. They are *external* pointers as
opposed to *internal* pointers, which originate inside the memory pool and point to another memory location within the
memory pool.

Here are some examples of GC roots:

1. Stack frames
2. JNI
3. Registers
4. Code roots (from the JVM code cache)
5. Globals
6. Class metadata from loaded classes

The HotSpot garbage collector works in terms of areas of memory called *arenas*.

HotSpot does not use system calls to manage the Java heap. Instead, HotSpot manages the heap size from user space code,
so we can use simple observables to determine whether the GC subsystem is causing some types of performance problems.

In the next section we'll take a closer look at two of the most important characteristics that drive the garbage
collection behavior of any Java or JVM workload.

## Allocation and Lifetime

There are two primary drivers of the garbage collection behavior of a Java application:

1. Allocation rate
2. Object lifetime

The **allocation rate** is the amount of memory used by newly created objects over some time period (usually measured in
MB/S). This is not directly recorded by the JVM, but is a relatively easy observable to estimate, and tools such as
Censum can determine it precisely

By contrast, the object lifetime is normally a lot harder to measure (or even estimate). In fact, one of the major
arguments against using manual memory management is the complexity involved in truly understanding object lifetimes for
a real application. As a result, object lifetime is if anything even more fundamental than allocation rate.

The idea that objects are created, they exist for a time, and then the memory used to store their state can be reclaimed
is essential; without it, garbage collection would not work at all.

### Weak Generational Hypothesis

One key part of the JVM's memory management relies upon an observed runtime effect of software systems, the **Weak
Generational Hypothesis** (WGH):

> The distribution of object lifetimes on the JVM and similar software systems
> is bimodal -- with the vast majority of objects being very short-lived and a secondary population having
> a much longer life expectancy.

This hypothesis, which is really an experimentally observed rule of thumb about the behavior of object-oriented
workloads, leads to an obvious conclusion: garbage-collected heaps should be structured in such a way as to allow
short-lived objects to be easily and quickly collected, and ideally for long-lived objects to be separated from
short-lived objects.

HotSpot uses several mechanism to try to take advantage of the WGH:

- It tracks the "generational count" of each object (the number of garbage collections that the object has survived so
  far)
- With the exception of large objects, it creates new objects in the "Eden" space (also called the "Nursery") and
  expects to move surviving objects
- It maintains a separate area of memory (the "old" or "tenured" generation) to hold objects that are deemed to have
  survived long enough that they are likely to be long-lived.

<img src="https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/gc01/images/gcslides/Slide5.png" alt="the heap structure">

## Garbage Collection in HotSpot

Recall that unlike C/C++ and similar environments, Java does not use the operating system to manage dynamic memory.
Instead, the JVM allocates (or reserves) memory up front, when the JVM process starts, and manages a single, contiguous
memory pool from users space.

This pool of memory is made up of different regions with dedicated purposes, and the address that an object resides at
will very often change over time as the collector relocates objects, which are normally created in Eden. Collectors that
perform relcation are known as "**Evacuating**" collectors. Many of the collectors that HotSpot can use are evacuating.

### Thread-Local Allocation

The JVM uses a performance enhancement to manage Eden. This is a critical region to manage efficiently, as it is where
most objects are created, and very short-lived objects will never be located elsewhere.

For efficiency, the JVM partitions Eden into buffers and hands out individual regions of Eden for application threads to
use as allocation regions for new objects. The advantage of this approach is that each thread knows that it does not
have to consider the possibility that other threads are allocating within that buffer. These regions are called *
thread-local allocation buffers* (TLABs).

> HotSpot dynamically sizes the TLABs that it gives to application threads, so if a thread is burning through memory it
> can be given larger TLABs to
> reduce the overhead in providing buffers to the thread.

The exclusive control that an application thread has over its TLABs means that allocation is **O(1)** for JVM threads.
This is because when a thread creates a new object, storage is allocated for the object, and the thread-local pointer is
updated to the next free memory address. In terms of the C runtime, this is a simple pointer bump.