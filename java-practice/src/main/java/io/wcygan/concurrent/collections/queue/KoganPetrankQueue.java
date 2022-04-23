package io.wcygan.concurrent.collections.queue;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Source:
 * https://github.com/pramalhe/ConcurrencyFreaks/blob/master/Java/com/concurrencyfreaks/queues/KoganPetrankQueue.java
 *
 * <h1>Kogan-Petrank Queue </h1>
 * <p>
 * Based on the Wait-Free queue by Alex Kogan and Erez Petrank
 * https://offblast.org/stuff/books/lockfreequeues_ppopp11.pdf
 * http://www.cs.technion.ac.il/~erez/Papers/wfquque-ppopp.pdf
 *
 * <p>This version has self-linking which we added ourselves
 *
 * <p>enqueue algorithm: Kogan-Petrank, based on the consensus of Lamport's bakery dequeue
 * algorithm: Kogan-Petrank, based on the consensus of Lamport's bakery Consistency: Linearizable
 * enqueue() progress: wait-free bounded O(N_threads) dequeue() progress: wait-free bounded
 * O(N_threads)
 *
 * @author Pedro Ramalhete
 * @author Andreia Correia
 */
public class KoganPetrankQueue<T> {

    private static final int NUM_THREADS = 128;
    // Unsafe mechanics
    private static final sun.misc.Unsafe UNSAFE;
    private static final long tailOffset;
    private static final long headOffset;

    static {
        try {
            Field f = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            UNSAFE = (sun.misc.Unsafe) f.get(null);
            Class<?> k = KoganPetrankQueue.class;
            tailOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("tail"));
            headOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("head"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    final AtomicReferenceArray<OpDesc<T>> state;
    // Member variables
    volatile Node<T> head;
    volatile Node<T> tail;

    public KoganPetrankQueue() {
        final Node<T> sentinel = new Node<T>(null, -1);
        head = sentinel;
        tail = sentinel;
        state = new AtomicReferenceArray<OpDesc<T>>(NUM_THREADS);
        for (int i = 0; i < state.length(); i++) {
            state.set(i, new OpDesc<T>(-1, false, true, null));
        }
    }

    private void help(long phase) {
        for (int i = 0; i < state.length(); i++) {
            OpDesc<T> desc = state.get(i);
            if (desc.pending && desc.phase <= phase) {
                if (desc.enqueue) {
                    help_enq(i, phase);
                } else {
                    help_deq(i, phase);
                }
            }
        }
    }

    private long maxPhase() {
        long maxPhase = -1;
        for (int i = 0; i < state.length(); i++) {
            long phase = state.get(i).phase;
            if (phase > maxPhase) {
                maxPhase = phase;
            }
        }
        return maxPhase;
    }

    private boolean isStillPending(int tid, long ph) {
        return state.get(tid).pending && state.get(tid).phase <= ph;
    }

    public void enq(T value) {
        // We better have consecutive thread ids, otherwise this will blow up
        // TODO: replace this mechanism with something more flexible
        final int TID = (int) (Thread.currentThread().getId() % NUM_THREADS);
        long phase = maxPhase() + 1;
        state.set(TID, new OpDesc<T>(phase, true, true, new Node<T>(value, TID)));
        help(phase);
        help_finish_enq();
    }

    private void help_enq(int tid, long phase) {
        while (isStillPending(tid, phase)) {
            Node<T> last = tail;
            Node<T> next = last.next;
            if (last == tail) { // If it's tail it can't be self-linked
                if (next == null) {
                    if (isStillPending(tid, phase)) {
                        if (last.casNext(next, state.get(tid).node)) {
                            help_finish_enq();
                            return;
                        }
                    }
                } else {
                    help_finish_enq();
                }
            }
        }
    }

    private void help_finish_enq() {
        final Node<T> last = tail;
        final Node<T> next = last.next;
        if (next != null && next != last) { // Check for self-linking
            int tid = next.enqTid;
            final OpDesc<T> curDesc = state.get(tid);
            if (last == tail && state.get(tid).node == next) {
                final OpDesc<T> newDesc = new OpDesc<T>(state.get(tid).phase, false, true, next);
                state.compareAndSet(tid, curDesc, newDesc);
                casTail(last, next);
            }
        }
    }

    public T deq() {
        // We better have consecutive thread ids, otherwise this will blow up
        // TODO: replace this mechanism with something more flexible
        final int TID = (int) (Thread.currentThread().getId() % NUM_THREADS);
        long phase = maxPhase() + 1;
        state.set(TID, new OpDesc<T>(phase, true, false, null));
        help(phase);
        help_finish_deq();
        final Node<T> node = state.get(TID).node;
        if (node == null) return null; // We return null instead of throwing an exception
        final T value = node.next.value;
        node.next = node; // Self-link to help the GC
        return value;
    }

    private void help_deq(int tid, long phase) {
        while (isStillPending(tid, phase)) {
            Node<T> first = head;
            Node<T> last = tail;
            Node<T> next = first.next;
            if (first == head) { // If it's still head then it's not self-linked
                if (first == last) {
                    if (next == null) {
                        OpDesc<T> curDesc = state.get(tid);
                        if (last == tail && isStillPending(tid, phase)) {
                            OpDesc<T> newDesc = new OpDesc<T>(state.get(tid).phase, false, false, null);
                            state.compareAndSet(tid, curDesc, newDesc);
                        }
                    } else {
                        help_finish_enq();
                    }
                } else {
                    OpDesc<T> curDesc = state.get(tid);
                    Node<T> node = curDesc.node;
                    if (!isStillPending(tid, phase)) break;
                    if (first == head && node != first) {
                        OpDesc<T> newDesc = new OpDesc<T>(state.get(tid).phase, true, false, first);
                        if (!state.compareAndSet(tid, curDesc, newDesc)) {
                            continue;
                        }
                    }
                    first.deqTid.compareAndSet(-1, tid);
                    help_finish_deq();
                }
            }
        }
    }

    private void help_finish_deq() {
        final Node<T> first = head;
        final Node<T> next = first.next;
        int tid = first.deqTid.get();
        if (tid != -1 && next != first) {
            final OpDesc<T> curDesc = state.get(tid);
            if (first == head && next != null) {
                final OpDesc<T> newDesc = new OpDesc<T>(state.get(tid).phase, false, false, state.get(tid).node);
                state.compareAndSet(tid, curDesc, newDesc);
                casHead(first, next);
            }
        }
    }

    private boolean casTail(Node<T> cmp, Node<T> val) {
        return UNSAFE.compareAndSwapObject(this, tailOffset, cmp, val);
    }

    private boolean casHead(Node<T> cmp, Node<T> val) {
        return UNSAFE.compareAndSwapObject(this, headOffset, cmp, val);
    }

    private static class Node<T> {
        // Unsafe mechanics
        private static final sun.misc.Unsafe UNSAFE;
        private static final long nextOffset;

        static {
            try {
                Field f = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
                f.setAccessible(true);
                UNSAFE = (sun.misc.Unsafe) f.get(null);
                Class<?> k = Node.class;
                nextOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("next"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }

        T value;
        volatile Node<T> next;
        int enqTid;
        AtomicInteger deqTid;

        public Node(T val, int etid) {
            value = val;
            next = null;
            enqTid = etid;
            deqTid = new AtomicInteger(-1);
        }

        public boolean casNext(Node<T> cmp, Node<T> val) {
            return UNSAFE.compareAndSwapObject(this, nextOffset, cmp, val);
        }
    }

    private static class OpDesc<T> {
        final long phase;
        final boolean pending;
        final boolean enqueue;
        final Node<T> node;

        public OpDesc(long ph, boolean pend, boolean enq, Node<T> n) {
            phase = ph;
            pending = pend;
            enqueue = enq;
            node = n;
        }
    }
}
