package com.hsy.guava;

import com.google.common.base.Objects;
import lombok.Data;

/**
 * @author huashaoyu
 * @title: Student
 * @projectName GuavaTest
 * @description: TODO
 * @date 2019/10/22 13:42
 */
@Data
public class Student {
    private String firstName;
    private String lastName;
    private int rollNo;
    private String className;

    public Student(String firstName, String lastName, int rollNo, String className) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rollNo = rollNo;
        this.className = className;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Student) || o == null){
            return false;
        }
        Student student = (Student) o;
        return rollNo == student.rollNo &&
                Objects.equal(firstName, student.firstName) &&
                Objects.equal(lastName, student.lastName) &&
                Objects.equal(className, student.className);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(rollNo, className);
    }
}
