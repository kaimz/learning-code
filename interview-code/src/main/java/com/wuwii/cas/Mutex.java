package com.wuwii.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by KronChan on 2018/5/17 15:52.
 */
public class Mutex implements Lock {
    // 自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer {
        /**
         * 判断是否是占有状态
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        // 尝试获取资源，立即返回。成功则返回true
        @Override
        public boolean tryAcquire(int acquires) {
            assert acquires == 1; // 这里限定只能为1个量
            if (compareAndSetState(0, 1)) {//state为0才设置为1，不可重入！
                setExclusiveOwnerThread(Thread.currentThread());//设置为当前线程独占资源
                return true;
            }
            return false;
        }

        // 尝试释放资源，立即返回。成功则为true，否则false。
        @Override
        protected boolean tryRelease(int releases) {
            assert releases == 1; // 限定为1个量
            if (getState() == 0)
                throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);//释放资源，放弃占有状态
            return true;
        }

        Condition newCondition() {
            return new ConditionObject();
        }
    }

    // 操作全部依赖于AQS自定义的同步器
    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    /**
     * 尝试获取资源，要求立即返回。成功则为true，失败则为false。
     */
    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    /**
     * 释放资源
     */
    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    /**
     * 锁是否占有状态
     */
    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    public static void main(String[] args) throws InterruptedException {
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
