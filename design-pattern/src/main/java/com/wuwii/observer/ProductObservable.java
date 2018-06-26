package com.wuwii.observer;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by KronChan on 18/6/26 上午10:06.
 */
public class ProductObservable {
    private List<Observer> observers = new CopyOnWriteArrayList<>();

    public ProductObservable(Observer... observers) {
        addObserver(observers);
    }

    protected void addObserver(Observer... observers) {
        this.observers.addAll(Arrays.asList(observers));
    }

    protected void removeObserver(Observer... observers) {
        this.observers.removeAll(Arrays.asList(observers));
    }

    public void BuyProductSuccessfully(String product) {
        for (Observer observer : observers) {
            observer.receive(product);
        }
    }
}
