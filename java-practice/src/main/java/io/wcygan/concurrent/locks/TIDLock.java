package io.wcygan.concurrent.locks;

import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * A lock based on ownership by Thread.currentThread()
 * <p>
 * {@link TIDLock} is safer than {@link TTASLock} because unlock will only succeed
 * if called by the owning thread.
 */
public class TIDLock implements Lock {

    private static final Thread NONE = null;
    AtomicReference<Thread> owner = new AtomicReference<>(NONE);

    @Override
    public void lock() {
        Thread current = Thread.currentThread();
        boolean acquired = false;
        while (!acquired) {
            if (NONE == owner.get()) {
                acquired = owner.compareAndSet(NONE, current);
            }
        }
    }

    @Override
    public boolean tryLock() {
        return owner.compareAndSet(NONE, Thread.currentThread());
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        Instant expiration = Instant.now().plusMillis(unit.toMillis(time));

        boolean acquired = false;
        while (Instant.now().isBefore(expiration) && !acquired) {
            if (owner.get() == NONE) {
                acquired = owner.compareAndSet(NONE, Thread.currentThread());
            }
        }

        return acquired;
    }

    @Override
    public void unlock() {
        if (owner.get() == Thread.currentThread()) {
            owner.set(NONE);
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new Error("Not Implemented");
    }

    @Override
    public Condition newCondition() {
        throw new Error("Not Implemented");
    }
}
