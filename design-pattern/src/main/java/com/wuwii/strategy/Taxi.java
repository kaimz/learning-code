package com.wuwii.strategy;

/**
 *
 */
public class Taxi implements Vehicle {

    @Override
    public String take() {
        return "Take the taxi is expensive";
    }
}
