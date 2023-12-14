package com.example.demo.Factory;

public class Client {


    public static void main(String[] args) {

        AuDiFactory AuDiFactory = new AuDiFactory();
        AuDiFactory.create();
        BMWFactory bmwFactory = new BMWFactory();
        bmwFactory.create();

    }
}
