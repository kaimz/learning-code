package com.wuwii.service;

import org.springframework.stereotype.Service;

/**
 * Created by KronChan on 2018/4/25 9:39.
 */
@Service
public class AliPay extends AbstractPay {
    @Override
    public void display(int money) {
        System.out.println("支付宝付款  " + money);
    }
}
