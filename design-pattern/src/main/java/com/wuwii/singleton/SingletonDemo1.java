package com.wuwii.singleton;

/**
 * 懒汉式，线程安全，懒加载 （不适用）
 *
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/21 15:53</pre>
 */
public class SingletonDemo1 {
    private static SingletonDemo1 instance;

    private SingletonDemo1() {

    }

    public static synchronized SingletonDemo1 getInstance() {
        if (instance == null) {
            instance = new SingletonDemo1();
        }
        return instance;
    }

    public static void main(String[] args) {
        SingletonDemo1 demo = SingletonDemo1.getInstance();
        demo.show();
    }

    public void show() {
        System.out.println("Hello World.");
    }
}
