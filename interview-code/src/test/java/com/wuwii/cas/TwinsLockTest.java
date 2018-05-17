package com.wuwii.cas;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**

 */
public class TwinsLockTest {
    @Test
    public void test1() throws InterruptedException {
        final Lock lock = new TwinsLock();

        class Worker extends Thread {
            @Override
            public void run() {
                try {
                    lock.lock();
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName());
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            new Worker().start();
        }

        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println();
        }
    }
}
