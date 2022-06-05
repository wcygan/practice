package io.wcygan.concurrent.examples.synchronizers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;

/**
 * We can imagine that there are race cars waiting for a race to begin...
 *
 * The race begins when all race cars signal that they are ready!
 *
 * <pre>
 *
 *                            ____________________
 *                      //|           |        \
 *                    //  |           |          \
 *       ___________//____|___________|__________()\__________________
 *     /__________________|_=_________|_=___________|_________________{}
 *     [           ______ |           | .           | ==  ______      { }
 *   __[__        /##  ##\|           |             |    /##  ##\    _{# }_
 *  {_____)______|##    ##|___________|_____________|___|##    ##|__(______}
 *              /  ##__##                              /  ##__##        \
 *
 * </pre>
 */
public class CountDownLatchExample {
    private static Integer NUM_RACE_CARS = 10;

    public static void main(String[] args) {

        /*
           The CountDownLatch will wait until its count reaches 0 before unblocking any waiting threads
        */
        CountDownLatch signal = new CountDownLatch(NUM_RACE_CARS);

        var tp = ForkJoinPool.commonPool();

        for (int i = 0; i < NUM_RACE_CARS; i++) {
            tp.execute(prepareRaceCar(i, signal));
        }

        tp.shutdown();
    }

    static Runnable prepareRaceCar(int id, CountDownLatch signal) {
        return () -> {
            try {
                // signal that we are ready to race
                signal.countDown();
                System.out.println("Racecar " + id + " is waiting!");

                // wait for every other race car to signal that they are ready (beware of deadlock here!)
                signal.await();

                // race!
                Thread.sleep(50);
                System.out.println("Racecar " + id + " is racing!!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
