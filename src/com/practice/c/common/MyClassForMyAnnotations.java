package com.practice.c.common;

import com.practice.c.my_annotation.MyClassAnnotation;
import com.practice.c.my_annotation.MyFieldAnnotation;
import com.practice.c.my_annotation.MyMethodAnnotation;

@MyClassAnnotation(fileName = "data")
public class MyClassForMyAnnotations {
    @MyFieldAnnotation
    String myField;

    String myField2;
    int myField3;

    @MyMethodAnnotation(name = "my_method")
    private void showMyClass(String somewhere) {
        System.out.println(" I am invoked from " + somewhere + "\n" + this);
    }

    public MyClassForMyAnnotations() {
        System.out.println("Ooura the class is created");
    }

    private void showMyClass2() {
        System.out.println("Another method. ooops");
    }

    @Override
    public String toString() {
        return "MyClassForMyAnnotations{" +
                "myField='" + myField + '\'' +
                '}';
    }
}
