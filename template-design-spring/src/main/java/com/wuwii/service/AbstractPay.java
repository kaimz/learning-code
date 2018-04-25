package com.wuwii.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by KronChan on 2018/4/25 9:37.
 */
@Service
public abstract class AbstractPay implements Pay {

    @PostConstruct
    public void init() {
        PAY_METHOD.put(this.getClass(), this);
    }

    protected boolean check(int money) {
        return money > 0;
    }

    @Override
    public void pay(int money) {
        if (check(money)) {
            display(money);
        }
    }

    public abstract void display(int money);
}
