package com.wuwii.factory;

/**
 * @author Zhang Kai
 * @version 1.0
 * @since <pre>2018/3/21 18:50</pre>
 */
public class CoderFactory {
    public static Object getCoder(Class<? extends Coding> clazz) {
        Object object = null;
        try {
            object = Class.forName(clazz.getName()).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static void main(String[] args) {
        CoderJava java = (CoderJava) CoderFactory.getCoder(CoderJava.class);
        java.code();
        CoderPython python = (CoderPython) CoderFactory.getCoder(CoderPython.class);
        python.code();
    }
}
