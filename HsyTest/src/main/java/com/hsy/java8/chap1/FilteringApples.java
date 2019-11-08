package com.hsy.java8.chap1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author huashaoyu
 * @title: FilteringApples
 * @projectName HsyTest
 * @description: TODO
 * @date 2019/11/1 14:44
 */
public class FilteringApples {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80,"green"),
                new Apple(155,"green"),
                new Apple(120,"red"));

        // 谓词，方法传递
        List<Apple> greenApples = filterApples(inventory, FilteringApples::isGreenApple);
        System.out.println(greenApples);
        List<Apple> heavyApples = filterApples(inventory, FilteringApples::isHeavyApple);
        System.out.println(heavyApples);
        
        // lambda
        List<Apple> greenApples1 = filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));
        List<Apple> heavyApples1 = filterApples(inventory, (Apple a) -> a.getWeight() > 150);
     
        
        List<Apple> heavyApples2 = inventory.stream().filter((Apple a) -> a.getWeight() > 150)
                .collect(Collectors.toList());

        heavyApples2.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });

        heavyApples2.sort((Apple o1, Apple o2) -> o1.getWeight() - o2.getWeight());
        
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world!");
            }
        });

        Thread thread1 = new Thread(() -> System.out.println("Hello world!"));

        Runnable r1 = () -> System.out.println("Hello world 1111");
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world 222");
            }
        };
        process(r1);
        process(r2);
        process(() -> System.out.println("Hello World"));

    }
    
    public static void process(Runnable r){
        r.run();
    }

    /**
     * 以下这两种方法，只有一个条件不同
     * @param inventory
     * @return
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() >= 150) {
                result.add(apple);
            }
        }
        return result;
    }
    
    public static boolean isGreenApple(Apple apple){
        return "green".equals(apple.getColor());
    }
    public static boolean isHeavyApple(Apple apple){
        return apple.getWeight() > 150;
    }
    
    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
    
    
}
