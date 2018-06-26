package com.wuwii.observer;

/**
 * Created by KronChan on 18/6/26 上午10:16.
 */
public class BuyObserver implements Observer {
    @Override
    public void receive(String product) {
        System.out.println(String.format("You have bought the %s successfully", product));
    }
}
