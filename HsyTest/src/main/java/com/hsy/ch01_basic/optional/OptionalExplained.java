package com.hsy.ch01_basic.optional;

import com.google.common.base.Optional;
import org.junit.Test;

/**
 * @author huashaoyu
 * @title: OptionalExplained
 * @projectName GuavaTest
 * @description: 使用和避免null
 * @date 2019/10/24 14:27
 */
public class OptionalExplained {
    
    @Test
    public void makingAnOptional(){
        Optional<Integer> possible = Optional.of(5);
//        Optional<Integer> possible1 = Optional.of(null);
        possible = Optional.fromNullable(5);
        possible = Optional.fromNullable(null);
    }
    
    @Test
    public void queryMethods(){
        Optional<Integer> a = Optional.fromNullable(null);
        Optional<Integer> b = Optional.of(5);
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("a or(3): " + a.or(3));
        System.out.println("a orNull: " + a.orNull());
        System.out.println("a or(b): " + a.or(b));
    }
    
    
    
}
