package com.practice.a.variant_1;

import com.practice.a.common.Resource;
import com.practice.a.common.StopWatch;

/**
 * Finds simple numbers and puts them into Resourse set
 */
public class SimpleCounterThread extends Thread {
    private final int startOfRange;
    private final int endOfRange;
    private final Resource resource;

    public SimpleCounterThread(String name, int startOfRange, int endOfRange, Resource resource) {
        super(name);
        this.startOfRange = startOfRange;
        this.endOfRange = endOfRange;
        this.resource = resource;
    }

    @Override
    public void run() {
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
                System.out.println(this.getName() + " hasn't added number " + progress + " of exception matter.");
            }
            synchronized (this.resource) {
                System.out.println(this.getName() + " has finished in its range and current condition of" +
                        "common set is:\n" + resource);
            }
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
