package com.hsy.java8.chap3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author huashaoyu
 * @title: ExecuteAround
 * @projectName HsyTest
 * @description: TODO
 * @date 2019/11/4 11:48
 */
public class ExecuteAround {
    
    public static String processFile() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
            return br.readLine();
        }
    }
    
    public static String processorFile(BufferedReaderProcess bp) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
            return bp.process(br);
        }
    }

    public static void main(String[] args) throws IOException {
//        String result = processorFile((BufferedReader br) -> br.readLine());
//        String result1 = processorFile((BufferedReader br) -> br.readLine() + br.readLine());
//
//        IntPredicate evenNumbers = (int i) -> i % 2 == 0;
//        evenNumbers.test(100);
//
//        Predicate<Integer> predicate = (Integer i) -> i % 2 == 0;
//        predicate.test(100);

        List<String> str = Arrays.asList("a","b","A","B");
        System.out.println(str);
        str.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        System.out.println(str);

        List<String> str1 = Arrays.asList("a","b","A","B");
        System.out.println(str1);
        str.sort(String::compareToIgnoreCase);
        System.out.println(str1);

        Function<String, Integer> stringToInteger = (String s) -> Integer.parseInt(s);
        Function<String, Integer> stringToInteger1 = Integer::parseInt;

        BiPredicate<List<String>, String> contains = (list, element) -> list.contains(element);
        BiPredicate<List<String>, String> contains1 = List::contains;

        Supplier<Apple> c1 = Apple::new;
        Apple a1 = c1.get();
        Supplier<Apple> c11 = () -> new Apple();
        Apple a11 = c1.get();
        
        Function<Integer, Apple> c2 = Apple::new;
        Apple a2 = c2.apply(500);
        Function<Integer, Apple> c22 = (weight) -> new Apple(weight);
        Apple a22 = c22.apply(500);

        BiFunction<Integer, String, Apple> c3 = Apple::new;
        Apple a3 = c3.apply(100, "green");
        BiFunction<Integer, String, Apple> c33 = (weight, color) -> new Apple(weight,color);
        Apple a33 = c33.apply(100, "green");

        List<String> str2 = Arrays.asList("a","b","A","B");
        str2.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        str2.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        
        
    }

    static Map<String, Function<Integer, Fruit>> map = new HashMap<>();
    static {
        map.put("apple", Apple::new);
        map.put("orange", Orange::new);
    }

    public static Fruit giveMeFruit(String fruit, Integer weight){
        return map.get(fruit.toLowerCase()).apply(weight);
    }

    @FunctionalInterface
    public interface BufferedReaderProcess{
        String process(BufferedReader br) throws IOException;
    }
    
}
