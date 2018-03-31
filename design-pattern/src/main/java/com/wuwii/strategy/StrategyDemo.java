package com.wuwii.strategy;

/**
 * @author KronChan
 */
public class StrategyDemo {

    public static String vehicle(String vehicle) {
        switch (vehicle) {
            case "taxi":
                return "Take the taxi is expensive";
            case "subway":
                return "Take the subway is inexpensive";
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(StrategyDemo.vehicle("taxi"));
        System.out.println(StrategyContext.strategyVehicle(Bike.class));
    }

}
