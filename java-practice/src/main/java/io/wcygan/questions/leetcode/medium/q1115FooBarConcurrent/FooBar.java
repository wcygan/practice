package io.wcygan.questions.leetcode.medium.q1115FooBarConcurrent;

// https://leetcode.com/problems/print-foobar-alternately/
class FooBar {
    boolean foo = true;
    boolean bar = false;

    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (this) {
                while (!foo) {
                    wait();
                }

                printFoo.run();
                foo = false;
                bar = true;
                notify();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (this) {
                while (!bar) {
                    wait();
                }

                printBar.run();
                bar = false;
                foo = true;
                notify();
            }
        }
    }
}
