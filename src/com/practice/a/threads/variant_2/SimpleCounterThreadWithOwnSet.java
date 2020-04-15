package com.practice.a.threads.variant_2;

import com.practice.a.common.Resource;
import com.practice.a.common.StopWatch;

import java.util.Set;

/**
 * Finds simple numbers and puts them into Resourse set
 */
public class SimpleCounterThreadWithOwnSet extends Thread {
    private final int startOfRange;
    private final int endOfRange;
    private final Resource resource;
    private final Set<Integer> ownSet;

    public SimpleCounterThreadWithOwnSet(String name, int startOfRange, int endOfRange, Resource resource,
                                         Set<Integer> ownSet) {
        super(name);
        this.startOfRange = startOfRange;
        this.endOfRange = endOfRange;
        this.resource = resource;
        this.ownSet = ownSet;
    }

    @Override
    public void run() {
        int progress = this.startOfRange;
        int border = this.endOfRange;
        while (progress <= border) {
            if (!isPrime(progress)) {
                progress++;
                continue;
            }
            if (!this.ownSet.contains(progress)) {
                this.ownSet.add(progress);
                progress++;
            }
        }
        synchronized (this.resource) {
            resource.addIndividualSet(this.ownSet);
            System.out.println(this.getName() + " has finished in its range and current condition of" +
                    "common set is:\n" + resource);
            StopWatch.fixEndTime();
            StopWatch.showCurrentDuration();
        }

    }

    private boolean isPrime(Integer number) {
        boolean flag = true;
        for (int i = 2; i < number / 2; i++) {
            if (number % i == 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    @Override
    public String toString() {
        return "SimpleCounterThread {" +
                "startOfRange=" + startOfRange +
                ", endOfRange=" + endOfRange +
                '}';
    }
}
