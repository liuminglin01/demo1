package com.example.demo;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapNotSafeDemo implements Runnable {
    private static ConcurrentHashMap<String, Integer> scores = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        scores.put("John", 0);
        Thread t1 = new Thread(new ConcurrentHashMapNotSafeDemo());
        Thread t2 = new Thread(new ConcurrentHashMapNotSafeDemo());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(scores);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Integer score = scores.get("John");
            Integer newScore = score + 1;
            scores.put("John", newScore);
        }
    }

//    public void run() {
//        for (int i = 0; i < 1000; i++) {
//
//            while (true) {
//                Integer score = scores.get("John");
//                Integer newScore = score + 1;
//                boolean result = scores.replace("John", score, newScore);
//                if (result) {
//                    break;
//                }
//
//            }
//        }
//    }





}
