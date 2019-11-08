package com.hsy.java8.chap1;

/**
 * @author huashaoyu
 * @title: AppleFancyFormatter
 * @projectName HsyTest
 * @description: TODO
 * @date 2019/11/1 17:03
 */
public class AppleSimpleFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        return "An apple of " + apple.getWeight() + "g";
    }
}
