package com.wuwii.singleton;

/**
 * 登记式，静态内部类，线程安全，懒加载
 *
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/21 16:39</pre>
 */
public class SingletonDemo4 {
    private SingletonDemo4() {

    }

    /**
     * 注意的是 》类的静态属性只会在第一次加载类的时候初始化，
     * 在初次加载 getInstance 方法时候才会去装载 SingletonHolder 静态类，从而有 Lazy-Loading的作用。
     */
    public static SingletonDemo4 getInstance() {
        return SingletonHolder.instance;
    }

    public static void main(String[] args) {
        SingletonDemo4.getInstance().show();
    }

    public void show() {
        System.out.println("Hello World.");
    }

    private static class SingletonHolder {
        static SingletonDemo4 instance = new SingletonDemo4();
    }
}
