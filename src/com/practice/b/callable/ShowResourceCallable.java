package com.practice.b.callable;

import com.practice.a.common.StopWatch;
import com.practice.b.common.Resource;
import com.practice.b.common.Sequence;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Shows conditions of Resource object
 */
public class ShowResourceCallable implements Callable<Resource> {
    private final String name;
    private final Resource resource;

    public ShowResourceCallable(String name, Resource resource) {
        this.name = "ShowResource" + name;
        this.resource = resource;
    }

    @Override
    public Resource call() throws Exception {
        System.out.println(this.name + " has started ... ");
        boolean flag = false;
        Sequence sequence;
        while (!flag) {
            System.out.println(this.name + " is waiting");
            TimeUnit.MILLISECONDS.sleep(200);
            synchronized (this.resource) {
                sequence = this.resource.getLongestSequence();
                if (sequence != null)
                    System.out.println("-> The longest sequence is:\n\t{ '" + sequence.getSequence() +
                            "' } with occurence: " + sequence.getOccurence());
                flag = this.resource.isFlag();
            }
        }
        System.out.println(this.name + " has ended.");
        StopWatch.fixEndTime();
        return this.resource;
    }
}
