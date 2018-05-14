package com.wuwii.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by KronChan on 2018/5/14 19:15.
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        int num = 10;
        Semaphore machines = new Semaphore(5);
        for (int i = 0; i < num; i++) {
            new Thread(new Worker(i, machines)).start();
        }
    }

    static class Worker extends Thread {
        private Semaphore machines;

        private int worker;

        Worker(int worker, Semaphore semaphore) {
            this.worker = worker;
            this.machines = semaphore;
        }
        @Override
        public void run() {
            try {
                machines.acquire();
                System.out.printf("工人 %d 开始使用机器工作了 %n", worker);
                TimeUnit.SECONDS.sleep((int) (Math.random() * 10));
                System.out.printf("工人 %d 干完活了，让出机器了%n", worker);
                machines.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
