package com.hsy.java8.chap1;

import java.util.List;

/**
 * @author huashaoyu
 * @title: MyAppleTest
 * @projectName HsyTest
 * @description: TODO
 * @date 2019/11/1 16:59
 */
public class MyAppleTest {
    
    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter){
        for (Apple apple : inventory) {
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }

}
