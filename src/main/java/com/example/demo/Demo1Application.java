package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@SpringBootApplication
public class Demo1Application {

    public static void wirte(int size) {
        // String sql = "INSERT INTO `sys_order_0`VALUES (%s, '202104201124', 100001, '广州*******有限公司', '',);";
//        System.out.println(String.format(sql,IdWorker.getId()));
        String path = "D:\\test.sql";
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int n = 5103459;
        int num = ++n;

        String sql = "INSERT INTO `sys_order_0`VALUES (%s, '202104201124', 100001, '广州*******有限公司', '',);";
        try {
            //符合Java一种设计模式:装饰者设计模式(过滤器:Filter)
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path));
            for (int i = 5103459; i < size; i++) {
                //写数据
                bos.write(String.format(sql, i).getBytes());
                if (i < size - 1) {
                    bos.write("\n".getBytes());
                }
            }
            //释放资源
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        //SpringApplication.run(Demo1Application.class, args);

        Demo1Application.wirte(5103458);
    }

}
