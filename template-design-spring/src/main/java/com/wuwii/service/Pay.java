package com.wuwii.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by KronChan on 2018/4/25 9:35.
 */
public interface Pay {
    Map<Class<? extends Pay>, Pay> PAY_METHOD = new ConcurrentHashMap<>();

    void pay(int money);
}
