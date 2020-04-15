package com.practice.a.callable;

import com.practice.a.common.Resource;
import com.practice.a.common.StopWatch;
import org.w3c.dom.ls.LSOutput;

import java.util.concurrent.Callable;

/**
 * Finds simple numbers and puts them into Resourse set
 */
public class SimpleCounterCallable implements Callable<String> {
    private final String name;
    private final int startOfRange;
    private final int endOfRange;
    private final Resource resource;

    public SimpleCounterCallable(String name, int startOfRange, int endOfRange, Resource resource) {
        this.name = name;
        this.startOfRange = startOfRange;
        this.endOfRange = endOfRange;
        this.resource = resource;
    }

    @Override
    public String call() {
        int progress = this.startOfRange;
        int border = this.endOfRange;
        while (progress <= border) {
            try {
                if (!isPrime(progress)) {
                    progress++;
                    continue;
                }
                if (!this.resource.isExist(progress)) {

                    this.resource.addToSet(progress);
                    progress++;
                }
            } catch (Exception ex) {
                System.out.println(this.name + " hasn't added number " + progress + " of exception matter.");
            }
        }
        String response = this.name + " has finished in its range in ";
        synchronized (this.resource) {
            StopWatch.fixEndTime();
            response += StopWatch.getCurrentDuration();
            response += " and current condition of common set is:\n" + this.resource;
        }
        return response;
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
