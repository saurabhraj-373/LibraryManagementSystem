package com.library.ExecutorsAndCallables;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BenchmarkingDemo {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(5);
        
        for(int i=1;i<=100_000;i++){
            IndividualNumberPrinter printer = new IndividualNumberPrinter(i);
            executor.submit(printer);
        }

        executor.shutdown(); // Initiates an orderly shutdown of the executor service
        // awaitTermination returns false on timeout, so loop until the pool is really done
        while (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
            System.out.println("Still waiting for tasks to finish...");
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}
