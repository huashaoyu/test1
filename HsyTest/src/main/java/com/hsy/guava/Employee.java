package com.hsy.guava;

import lombok.Data;

/**
 * @author huashaoyu
 * @title: Employee
 * @projectName GuavaTest
 * @description: TODO
 * @date 2019/10/23 13:26
 */
@Data
public class Employee {
    private String name;
    private String dept;
    private String empID;

    public Employee(String name, String dept, String empID) {
        this.name = name;
        this.dept = dept;
        this.empID = empID;
    }
    
}
