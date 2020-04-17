package com.practice.c;

import com.practice.c.handlerForMyAnnotations.MyAnnotationHandler;

public class Demo {
    public static void main(String[] args) {
        String packageUrl = "com.practice.c";
        MyAnnotationHandler.showCreatedObjectOfMyAnnotatedClasses(packageUrl);
    }
}
