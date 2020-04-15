package com.practice.a;

import com.practice.a.callable.TaskExecutorServiceResolver;
import com.practice.a.common.Console;
import com.practice.a.common.RangeDistributor;
import com.practice.a.common.Resource;
import com.practice.a.threads.variant_1.TaskResolverCommonSet;
import com.practice.a.threads.variant_2.TaskResolverIndividualSet;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Demo {
    public static void main(String[] args) {
        Set<Integer> commonSet = new TreeSet<>();
        Resource resource = new Resource(commonSet);
        Console console = new Console();
        console.getDataFromUser();
        RangeDistributor rangeDistributor = new RangeDistributor(console);
        Thread[] threads = new Thread[console.getN()];

//        TaskResolverCommonSet taskResolverCommonSet = new TaskResolverCommonSet(rangeDistributor, resource, threads);
//        taskResolverCommonSet.runThreads();

//        TaskResolverIndividualSet taskResolverIndividualSet = new TaskResolverIndividualSet(rangeDistributor, resource, threads);
//        taskResolverIndividualSet.runThreads();

        ExecutorService executorService = Executors.newFixedThreadPool(console.getN());
        Future<String>[] futures = new Future[console.getN()];
        TaskExecutorServiceResolver taskExecutorServiceResolver = new TaskExecutorServiceResolver(rangeDistributor, resource,
                executorService, futures);
        taskExecutorServiceResolver.runThreads();

    }
}
