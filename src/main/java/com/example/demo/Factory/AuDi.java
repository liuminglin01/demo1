package com.example.demo.Factory;

/**
 * 车的抽象类
 */
public class AuDi extends Car {



    @Override
    public void bulid() {
        System.out.println("生产一台AuDi");
    }


    public static void main(String[] args) {
        String str = "C";
        int size = str.getBytes().length;
        System.out.println("单个字符字符串占用内存大小：" + size + "字节");
    }

}
