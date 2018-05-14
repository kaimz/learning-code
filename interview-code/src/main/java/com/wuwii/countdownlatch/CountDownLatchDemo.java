package com.wuwii.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 如有Thread1、Thread2、Thread3、Thread4四条线程分别统计C、D、E、F四个盘的大小，
 * 所有线程都统计完毕交给Thread5线程去做汇总，应当如何实现？
 *
 * Created by KronChan on 2018/5/14 17:00.
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 初始化计数器，设置总量i，调用一次countDown(）方法后i的值会减1。
        // 在一个线程中如果调用了await()方法，这个线程就会进入到等待的状态，当参数i为0的时候这个线程才继续执行。
        final CountDownLatch countDownLatch = new CountDownLatch(4);
        Runnable thread1 = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("统计 C 盘大小");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 统计完成计数器 -1
            countDownLatch.countDown();
        };
        Runnable thread2 = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("统计 D 盘大小");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        };
        Runnable thread3 = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("统计 E 盘大小");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        };
        Runnable thread4 = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("统计 F 盘大小");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        };

        ExecutorService pool = Executors.newFixedThreadPool(4);
        pool.execute(thread1);
        pool.execute(thread2);
        pool.execute(thread3);
        pool.execute(thread4);
        // 等待 i 值为 0 ，等待四条线程执行完毕。
        countDownLatch.await();
        System.out.println("统计完成");
        pool.shutdown();
    }
}
