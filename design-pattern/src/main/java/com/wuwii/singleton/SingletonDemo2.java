package com.wuwii.singleton;

/**
 * 饿汉式，线程安全，没有懒加载
 *
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/21 16:34</pre>
 */
public class SingletonDemo2 {
    private static SingletonDemo2 instance = new SingletonDemo2();

    private SingletonDemo2() {

    }

    public static SingletonDemo2 getInstance() {
        return instance;
    }
}
