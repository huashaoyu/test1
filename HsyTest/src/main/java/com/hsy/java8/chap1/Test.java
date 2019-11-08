package com.hsy.java8.chap1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author huashaoyu
 * @title: Test
 * @projectName HsyTest
 * @description: TODO
 * @date 2019/11/1 17:22
 */
public class Test {

    public final int value = 4;
    public void doIt()
    {
        int value = 6;
        Runnable r = new Runnable(){
            public final int value = 5;
            @Override
            public void run(){
                int value = 10;
                System.out.println(this.value);
            }
        };
        r.run();
    }
    public static void main(String...args)
    {
        List list = new ArrayList<>();
        Optional<Integer> a = Optional.of(5);
        a.ifPresent(b -> list.add(b));
        
    }
}
