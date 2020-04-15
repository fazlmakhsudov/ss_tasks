package com.practice.a.threads.variant_1;

import com.practice.a.common.RangeDistributor;
import com.practice.a.common.Resource;
import com.practice.a.common.StopWatch;

import java.util.Arrays;

/**
 * Initializes threads in order and runs them
 */
public class TaskResolverCommonSet {
    private final RangeDistributor rangeDistributor;
    private final Resource resource;
    private final Thread[] threads;

    public TaskResolverCommonSet(RangeDistributor rangeDistributor, Resource resource, Thread[] threads) {
        this.rangeDistributor = rangeDistributor;
        this.resource = resource;
        this.threads = threads;
        this.initializeThreads();
    }

    public void runThreads() {
        StopWatch.fixStartTime();
        for (Thread thread : this.threads) {
            thread.start();
        }
    }

    private void initializeThreads() {
        int n = rangeDistributor.getN();
        for (int i = 0; i < n; i++) {
            this.threads[i] = new SimpleCounterThread("Thread_" + i, this.rangeDistributor.getStartRangeForThread(i),
                    this.rangeDistributor.getEndRangeForThread(i), this.resource);
            System.out.println(threads[i]);
        }
    }

    @Override
    public String toString() {
        return "TaskResolverCommonSet{" +
                "rangeDistributor=" + rangeDistributor +
                ", resource=" + resource +
                ", threads=" + Arrays.toString(threads) +
                '}';
    }
}
