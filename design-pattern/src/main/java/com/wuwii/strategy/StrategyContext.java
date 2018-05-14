package com.wuwii.strategy;

import com.sun.istack.internal.Nullable;

/**
 * 使用上下文，
 */
public class StrategyContext {
    @Nullable
    public static Vehicle strategyVehicle(Class<? extends Vehicle> clazz) {
        if (clazz == null) {
            return null;
        }
        try {
            return (Vehicle) Class.forName(clazz.getName()).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
