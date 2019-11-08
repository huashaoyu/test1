package com.hsy.java8.chap1;

/**
 * @author huashaoyu
 * @title: AppleFancyFormatter
 * @projectName HsyTest
 * @description: TODO
 * @date 2019/11/1 17:03
 */
public class AppleFancyFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
        return "A " + characteristic + " " + apple.getColor() + " apple";
    }
}
