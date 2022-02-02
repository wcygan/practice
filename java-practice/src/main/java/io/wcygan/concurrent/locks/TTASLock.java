package io.wcygan.concurrent.locks;

import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class TTASLock implements Lock {

    private final AtomicBoolean locked = new AtomicBoolean(false);

    @Override
    public void lock() {
        boolean acquired = false;
        while (!acquired) {
            if (!locked.get()) {
                acquired = locked.compareAndSet(false, true);
            }
        }
    }

    @Override
    public boolean tryLock() {
        if (locked.get()) {
            return false;
        }

        return locked.compareAndSet(false, true);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        Instant expiration = Instant.now().plusMillis(unit.toMillis(time));

        boolean acquired = false;
        while (Instant.now().isBefore(expiration) && !acquired) {
            if (!locked.get()) {
                acquired = locked.compareAndSet(false, true);
            }
        }

        return acquired;
    }

    @Override
    public void unlock() {
        locked.set(false);
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
