package com.practice.b.callable;

import com.practice.b.common.Resource;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.Callable;

/**
 * Loads data from certain file
 */
public class FileLoaderCallable implements Callable<Resource> {
    private final String name;
    private final Resource resource;

    public FileLoaderCallable(String name, Resource resource) {
        this.name = "FileLoader" + name;
        this.resource = resource;
    }

    @Override
    public Resource call() throws Exception {
        synchronized (this.resource) {
            System.out.println(this.name + " has started ... ");
            String filePath = this.resource.getPath() + File.separator + this.resource.getActiveFileName();
            try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(filePath))) {
                byte[] data = is.readAllBytes();
                this.resource.setData(new String(data));
            }
            System.out.println(this.name + " has ended.");
        }
        return this.resource;
    }
}
