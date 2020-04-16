package com.practice.b.callable;

import com.practice.a.common.StopWatch;
import com.practice.b.common.Resource;
import com.practice.b.common.Sequence;

import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Initializes threads in order and runs them
 */
public class TaskExecutorService {
    private final String path;
    private final Scanner scanner;
    private final String pattern;
    private final Resource resource;
    private ExecutorService executorService;
    private Future<Resource> future;
    private Callable[] callables;

    public TaskExecutorService(Scanner scanner, String pattern, Resource resource,
                               ExecutorService executorService) {
        this.scanner = scanner;
        this.pattern = pattern;
        this.resource = resource;
        this.path = resource.getPath();
        this.executorService = executorService;
    }

    public void setCallables(Callable[] callables) {
        this.callables = callables;
    }

    public void runThreads() {
        StopWatch.fixStartTime();
        int i = 1;
        boolean flag = true;
        do {
            this.initializeThreads(i);
            this.executorService.shutdown();
            try {
                Resource resource = this.future.get();
                Sequence sequence = resource.getLongestSequence();
                System.out.println("\n->> Finally the longest sequence is:" + sequence +
                        "\nThis was end of processing file: " + this.resource.getActiveFileName());
            } catch (InterruptedException | ExecutionException e) {
                System.out.printf("Sorry, " + e.getMessage());
                e.printStackTrace();
            }
            System.out.println("\n Would you like to check another file? yes/?");
            flag = this.scanner.next().trim().equalsIgnoreCase("yes");
            if (flag) {
                this.executorService = Executors.newFixedThreadPool(1);
                this.resource.reset();
            }
        } while (flag);
        StopWatch.showCurrentDuration();
    }

    private void initializeThreads(int i) {
        this.callables[0] = new ConsoleCallable("_" + i, this.resource, this.path, this.scanner);
        this.callables[1] = new FileLoaderCallable("_" + i, this.resource);
        this.callables[2] = new DataProcessCallable("_" + i, this.resource, this.pattern);
        this.callables[3] = new ShowResourceCallable("_" + i, this.resource);
        this.executorService.submit(this.callables[0]);
        this.executorService.submit(this.callables[1]);
        this.executorService.submit(this.callables[2]);
        this.future = this.executorService.submit(this.callables[3]);
    }

}
