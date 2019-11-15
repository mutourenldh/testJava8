package com.java8;

import java.sql.SQLOutput;

/**
 * @author lidonghao
 * @create 2019-11-15 9:43
 * 从匿名内部类到lambda表达式
 */
public class newTest1115 {

    public static void main(String[] args) {
//        匿名内部类的写法
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                System.out.println("Hello World");
            }
        };
        runnable.run();
//        lambda表达式的写法1
        Runnable runnable1 = () -> {
            System.out.println("Hello World111!");
        };
        runnable1.run();
//      lambda表达式的写法2
        Runnable r1= () -> System.out.println("1111");
        r1.run();
    }
}
