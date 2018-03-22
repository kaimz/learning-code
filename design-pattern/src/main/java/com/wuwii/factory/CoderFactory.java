package com.wuwii.factory;

import com.sun.istack.internal.NotNull;

/**
 * 工厂模式，直接利用面向对象和反射实现工厂
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/21 18:50</pre>
 */
public class CoderFactory {
    public static Object getCoder(@NotNull Class<? extends Coding> clazz) {
        Object object = null;
        try {
            object = Class.forName(clazz.getName()).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static void main(String[] args) {
        Coding java = (CoderJava) CoderFactory.getCoder(CoderJava.class);
        java.code();
        Coding python = (CoderPython) CoderFactory.getCoder(CoderPython.class);
        python.code();
    }
}
