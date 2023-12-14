package com.example.demo.Factory;

public class AuDiFactory extends Factory{


    @Override
    public Car create() {
        return new AuDi();
    }
}
