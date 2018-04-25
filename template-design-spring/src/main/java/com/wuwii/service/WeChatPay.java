package com.wuwii.service;

import org.springframework.stereotype.Service;

/**
 * Created by KronChan on 2018/4/25 9:42.
 */
@Service
public class WeChatPay extends AbstractPay {
    @Override
    public void display(int money) {
        System.out.println("WeChat pay " + money);
    }
}
