package com.library.ExecutorsAndCallables;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OneToMillionPrintUsingThread {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 1_000_000; i++) {
            int number = i;
            executor.submit(() -> System.out.println(Thread.currentThread().getName() + ": " + number));
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.MINUTES);
        System.out.println("Done");
    }
}

