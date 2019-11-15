package com.java8;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author lidonghao
 * @create 2019-11-15 9:58
 */
public class test2 {
    public static void main(String[] args) {
//        原来的使用匿名内部类作为参数进行传递
        TreeSet<String> t1 = new TreeSet<String>(new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return Integer.compare(s.length(),t1.length());
            }
        });
//        使用lambda表达式作为参数进行传递
        TreeSet<String> t2 = new TreeSet<>(
                (o1,o2) -> Integer.compare(o1.length(),o2.length())
        );
    }
}
