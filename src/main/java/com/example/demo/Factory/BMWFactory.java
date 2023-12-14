package com.example.demo.Factory;

public class BMWFactory extends Factory {


    @Override
    public Car create() {
        return new BMW();
    }
}
