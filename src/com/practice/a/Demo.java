package com.practice.a;

import com.practice.a.common.Console;
import com.practice.a.common.RangeDistributor;
import com.practice.a.common.Resource;
import com.practice.a.variant_2.TaskResolverIndividualSet;

import java.util.Set;
import java.util.TreeSet;

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
//        commonSet.clear();

        TaskResolverIndividualSet taskResolverIndividualSet = new TaskResolverIndividualSet(rangeDistributor, resource, threads);
        taskResolverIndividualSet.runThreads();

    }
}
