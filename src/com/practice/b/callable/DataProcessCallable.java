package com.practice.b.callable;

import com.practice.b.common.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataProcessCallable implements Callable<Resource> {
    private final String name;
    private final Resource resource;
    private final String pattern;

    public DataProcessCallable(String name, Resource resource, String pattern) {
        this.name = "DataProcess" + name;
        this.resource = resource;
        this.pattern = pattern;
    }

    @Override
    public Resource call() throws Exception {
        Pattern p = Pattern.compile(this.pattern);
        Matcher m;
        String data = "";
        List<String> sequences = new ArrayList<>();
        synchronized (this.resource) {
            System.out.println(this.name + " has started ...");
            data = this.resource.getData();
            m = p.matcher(data);
            while (m.find()) {
                String key = m.group();
                int index = m.start();
                this.resource.addSequence(key);
                sequences.add(key);
            }
        }
        TimeUnit.MILLISECONDS.sleep(200);
        for (String seq : sequences) {
            p = Pattern.compile(seq);
            m.usePattern(p);
            m.reset();
            synchronized (this.resource) {
                while (m.find()) {
                    int index = m.start();
                    String key = m.group();
                    this.resource.increaseSequenceNumber(seq);
                    this.resource.addIndex(key, index);
                }
                System.out.println(this.name + " is waiting");
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }
        synchronized (this.resource) {
            this.resource.setFlag(true);
            System.out.println(this.name + " has ended.");
        }
        return resource;
    }
}
