package com.practice.b.callable;

import com.practice.b.common.Resource;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Callable;

/**
 * Cooperates with user to
 * determine file that should be processed
 */
public class ConsoleCallable implements Callable<Resource> {
    private final String name;
    private final Resource resource;
    private final Scanner scanner;
    private final String path;

    public ConsoleCallable(String name, Resource resource, String path, Scanner scanner) {
        this.resource = resource;
        this.name = "ConsoleCallable" + name;
        this.scanner = scanner;
        this.path = this.resource.getPath();
    }

    @Override
    public Resource call() throws Exception {
        this.getDataFromUser();
        return this.resource;
    }


    private void getDataFromUser() {
        synchronized (this.resource) {
            System.out.println(this.name + " has started ...");
            File[] files = this.listFileNames(this.scanner);
            this.resource.setFiles(files);
            String fileName = this.askFileName(this.scanner, files);
            this.resource.setActiveFileName(fileName);
            System.out.println(this.name + " has ended.");
        }
    }

    private File[] listFileNames(Scanner scanner) {
        File dir = new File(this.path);
        File[] files = dir.listFiles();
        System.out.println("FILE NAME     SIZE");
        for (int i = 0; i < files.length; i++) {
            System.out.print(files[i].getName() + " \t| " + files[i].length() + "\t|");
        }
        System.out.println();
        return files;
    }

    private String askFileName(Scanner scanner, File[] files) {
        String fileName = "";
        boolean flag = true;
        do {
            System.out.println("Type filename according to existing names: ");
            String name = scanner.next();
            if (name.trim().isEmpty()) {
                continue;
            }
            flag = Arrays.stream(files).anyMatch((f) -> f.getName().equalsIgnoreCase(name));
            if (flag) {
                fileName = name;
            }
        } while (!flag);
        return fileName;
    }
}
