package com.example.demo.Factory;

public class Test {


    public Test() {
    }

    public static void sest(Integer interger) {
        System.out.println(interger);
    }

    public static void sest(Number num) {
        System.out.println("num");
    }

    public static void sest(String s) {
        System.out.println("33333");
    }

    public static void sest(Object o) {
        System.out.println("44444");
    }

    public static void main(String[] args) {
        Integer q = 1;
        sest((int) q);

        Object obj = q;

        sest(obj);

        Number num = 1;
        int a = (int) num;
        sest((int) a);


    }

}
