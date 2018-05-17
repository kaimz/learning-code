package com.wuwii.cas;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by KronChan on 2018/5/17 17:14.
 */
public class MutexTest {
    @Test
    public void test() throws InterruptedException {
        Mutex mutex = new Mutex();
        for (int i = 0; i < 5; i++) {
            final int j = i;
            new Thread(() -> {
                try {
                    mutex.lock();
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(j);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mutex.unlock();
                }
            }).start();

            TimeUnit.SECONDS.sleep(1);
            System.out.println("------------");
        }
    }
}
