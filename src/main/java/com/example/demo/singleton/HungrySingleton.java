package com.example.demo.singleton;


/**
 * 饿汉式
 */
public class HungrySingleton {

    //定义一个私有的类的静态实例
    private static HungrySingleton instance = new HungrySingleton();


    //构造器设置为private
    private HungrySingleton() {
    }

    //提供一个公有的获取实例的静态方法。
    public static HungrySingleton getInstance() {
        return instance;
    }


    public static void main(String[] args) {
        System.out.println(getInstance());
        System.out.println(getInstance());
    }


}
