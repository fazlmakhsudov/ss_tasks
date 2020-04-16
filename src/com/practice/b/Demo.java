package com.practice.b;

import com.practice.b.callable.TaskExecutorService2;
import com.practice.b.common.Resource;
import com.practice.b.common.Resource2;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String path = "src" + File.separator + "com" + File.separator + "practice" + File.separator + "b" +
                File.separator + "my_files";
        String pattern = "((.)+)(\\1)+";
        Resource resource = new Resource(path, new TreeMap<>(), new TreeSet<>());
        Resource2 resource2 = new Resource2(path, new ArrayList<>());
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        TaskExecutorService2 taskExecutorService = new TaskExecutorService2(scanner, pattern, resource2, executorService);
        taskExecutorService.setCallables(new Callable[4]);
        try {
            taskExecutorService.runThreads();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scanner.close();
        System.out.println("************");
    }
}
