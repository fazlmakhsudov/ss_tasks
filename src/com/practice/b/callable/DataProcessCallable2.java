package com.practice.b.callable;

import com.practice.b.common.Resource2;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataProcessCallable2 implements Callable<Resource2> {
    private final String name;
    private final Resource2 resource;
    private final String pattern;

    public DataProcessCallable2(String name, Resource2 resource, String pattern) {
        this.name = "DataProcess" + name;
        this.resource = resource;
        this.pattern = pattern;
    }

    @Override
    public Resource2 call() throws Exception {
        Pattern p = Pattern.compile(this.pattern);
        Matcher m = null;
        String data = "";
        this.findAllMatches(data, p, m);
        this.findAllOccurencesOfLongestSequence(p, m);

        synchronized (this.resource) {
            this.resource.setFlag(true);
            System.out.println(this.name + " has ended.");
        }
        return this.resource;
    }

    private void findAllMatches(String data, Pattern p, Matcher m) {
        synchronized (this.resource) {
            System.out.println(this.name + " has started ...");
            data = this.resource.getData();
            m = p.matcher(data);
            while (m.find()) {
                String key = m.group();
                this.resource.addSequence(key);
            }
            System.out.println(this.resource.getAllSequences(this.pattern));
        }

    }

    private void findAllOccurencesOfLongestSequence(Pattern p, Matcher m) throws InterruptedException {
        synchronized (this.resource) {
            String pattern = this.resource.getSequence().getSequence();
            p = Pattern.compile(pattern);
            m = p.matcher(this.resource.getData());
        }
        while (m.find()) {
            int index = m.start();
            synchronized (this.resource) {
                this.resource.increaseSequenceNumber();
                this.resource.addIndex(index);
            }
            System.out.println(this.name + " is waiting");
            TimeUnit.MILLISECONDS.sleep(200);
        }
    }
}
