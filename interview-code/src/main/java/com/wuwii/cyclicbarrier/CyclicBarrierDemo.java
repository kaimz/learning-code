package com.wuwii.cyclicbarrier;

import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * 实现让一组线程等待至某个状态之后，再全部一起同时执行下面的，
 *
 *
 * Created by KronChan on 2018/5/14 17:35.
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        String[] drivers = {"C", "D", "E", "F"};
        int length = drivers.length;
        ExecutorService pool = Executors.newFixedThreadPool(length);
        // 如果线程都到达barrier状态后，会从四个线程中选择一个线程去执行Runnable。
        CyclicBarrier cyclicBarrier = new CyclicBarrier(length, () -> {
            System.out.printf("%s 线程告诉你，统计完毕，待继续执行%n", Thread.currentThread().getName());
        });
        Stream.of(drivers).forEach((d) -> {
            pool.execute(new StatisticsDemo(d, cyclicBarrier));
        });
        pool.shutdown();
    }

    static class StatisticsDemo implements Runnable {

        private String driveName;

        private CyclicBarrier cyclicBarrier;

        public StatisticsDemo(String driveName, CyclicBarrier cyclicBarrier) {
            this.driveName = driveName;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep((int) (Math.random() * 10));
                System.out.printf("%s 线程统计 %s 盘大小%n", Thread.currentThread().getName(), driveName);
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.printf("%s 准备退出%n", driveName);
        }
    }
}


