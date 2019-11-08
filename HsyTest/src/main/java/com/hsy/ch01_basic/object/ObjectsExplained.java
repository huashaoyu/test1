package com.hsy.ch01_basic.object;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.junit.Test;

/**
 * @author huashaoyu
 * @title: ObjectsExplained
 * @projectName GuavaTest
 * @description: 常见Object方法
 * @date 2019/10/24 14:11
 */
public class ObjectsExplained {
    
    String a = "123";
    String b = "456";

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    @Test
    public void equalsMethod(){
        System.out.println(Objects.equal("a","a"));
        System.out.println(Objects.equal(null,"a"));
        System.out.println(Objects.equal("a",null));
        System.out.println(Objects.equal(null, null));
    }
    
    @Test
    public void hashCodeMethod(){
        int hash = Objects.hashCode(2,null,"123");
        int hash1 = Objects.hashCode(null,2,"123");
        System.out.println(hash);
        System.out.println(hash1);
    }
    
    @Test
    public void toStringHelperMethod(){
        String s1 = MoreObjects.toStringHelper(this)
                .add("x",1)
                .toString();
        System.out.println(s1);

        String s2 = MoreObjects.toStringHelper("MyObject")
                .add("x", 1)
                .toString();
        System.out.println(s2);
    }
}
