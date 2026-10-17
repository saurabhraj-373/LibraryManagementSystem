package com.library.Synchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AddSubDemo {
    public static void main(String[] args) throws Exception {
        Counter counter = new Counter(0);

        AdderTask adderTask = new AdderTask(counter);
        SubtractorTask subtractorTask = new SubtractorTask(counter);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Void> adderFuture = executorService.submit(adderTask);
        Future<Void> subtractorFuture = executorService.submit(subtractorTask);

        adderFuture.get();
        subtractorFuture.get();

        System.out.println("Final value of counter: " + counter.getX());

        executorService.shutdownNow();
    }
}
