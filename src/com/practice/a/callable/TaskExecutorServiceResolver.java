package com.practice.a.callable;

import com.practice.a.common.RangeDistributor;
import com.practice.a.common.Resource;
import com.practice.a.common.StopWatch;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Initializes threads in order and runs them
 */
public class TaskExecutorServiceResolver {
    private final RangeDistributor rangeDistributor;
    private final Resource resource;
    private final ExecutorService executorService;
    private final Future<String>[] futures;

    public TaskExecutorServiceResolver(RangeDistributor rangeDistributor, Resource resource,
                                       ExecutorService es, Future<String>[] futures) {
        this.rangeDistributor = rangeDistributor;
        this.resource = resource;
        this.executorService = es;
        this.futures = futures;
    }

    public void runThreads() {
        StopWatch.fixStartTime();
        this.initializeThreads();
        this.executorService.shutdown();
        for (Future<String> future : this.futures) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                System.out.printf("Sorry, " + e.getMessage());
            }
        }
    }

    private void initializeThreads() {
        int n = rangeDistributor.getN();
        for (int i = 0; i < n; i++) {
            this.futures[i] = this.executorService.submit(new SimpleCounterCallable("CallableThread_" + i,
                    this.rangeDistributor.getStartRangeForThread(i),
                    this.rangeDistributor.getEndRangeForThread(i), this.resource));
        }
        System.out.println("...");
    }

    @Override
    public String toString() {
        return "TaskResolverCommonSet{" +
                "rangeDistributor=" + rangeDistributor +
                ", resource=" + resource +
                '}';
    }
}
