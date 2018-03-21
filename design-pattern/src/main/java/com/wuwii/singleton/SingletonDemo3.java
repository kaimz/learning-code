package com.wuwii.singleton;

/**
 * 　Double-Check 双重检查，线程安全，懒加载, jdk > 1.5
 *
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/21 16:35</pre>
 */
public class SingletonDemo3 {
    private volatile static SingletonDemo3 instance;

    private SingletonDemo3() {

    }

    /**
     * 为了减少同步的开销，采用双重检查
     */
    public static SingletonDemo3 getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo3.class) {
                if (instance == null) {
                    instance = new SingletonDemo3();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        SingletonDemo3 demo = SingletonDemo3.getInstance();
        demo.show();
    }

    public void show() {
        System.out.println("Hello World.");
    }
}
