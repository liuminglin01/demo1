package com.example.demo.singleton;

/**
 * 懒汉式 在第一次使用的时候才进行初始化，懒加载；由于获取实例的静态方法用synchronized修饰，线程安全；每次获取实例都要进行同步（加锁），因此效率较低
 */
public class LazySingleto {

    //定义一个私有的类的静态实例 (延迟初始化，线程安全)
    private static LazySingleto instance;

    //构造器设置为private
    private LazySingleto() {
    }

    //提供一个公有的获取实例的静态方法(用synchronized修饰)。
    public static synchronized LazySingleto getInstance() {
        if (null == instance) {
            instance = new LazySingleto();
        }
        return instance;
    }


    //双重检测机制（DCL 双重检查加锁） 在第一次使用的时候才进行初始化，达到了懒加载；在进行初始化的时候会进行同步（加锁），因此没有线程安全问题；
    // 并且只有第一次进行初始化才进行同步，因此不会有效率方面的问题。

    public static LazySingleto getInstance1() {
        if (null == instance) {
            synchronized (LazySingleto.class) {
                if (null == instance) {
                    instance = new LazySingleto();
                }
            }
        }
        return instance;
    }


    public static void main(String[] args) {
        System.out.println(getInstance());
        System.out.println(getInstance1());
    }
}
