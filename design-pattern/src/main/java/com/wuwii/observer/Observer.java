package com.wuwii.observer;

/**
 * Created by KronChan on 18/6/26 上午10:01.
 */
public interface Observer {
    /**
     * The observer receive the semaphore.
     */
    void receive(String product);
}
