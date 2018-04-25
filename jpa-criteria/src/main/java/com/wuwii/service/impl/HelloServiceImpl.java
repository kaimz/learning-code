package com.wuwii.service.impl;

import com.wuwii.service.HelloService;

/**
 * Created by KronChan on 2018/4/25 16:05.
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void say() {
        System.out.println("=====>>>> Hello.");
    }
}
