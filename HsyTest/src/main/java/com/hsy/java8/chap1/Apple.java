package com.hsy.java8.chap1;

/**
 * @author huashaoyu
 * @title: Apple
 * @projectName HsyTest
 * @description: TODO
 * @date 2019/11/1 17:01
 */
public class Apple {

    private int weight = 0;
    private String color = "";

    public Apple(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }

}
