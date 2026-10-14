package com.library.ExecutorsAndCallables;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CallableDemo {
    public static void main(String[] args) throws InterruptedException {
        NumberMultiplier multiplier = new NumberMultiplier(100);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        // Runnable -> Execute
        // Callable -> Submit

        Future<Integer> output = executor.submit(multiplier);

        try {
            Integer result = output.get();
            System.out.println("The result is: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }


        executor.shutdown(); // Initiates an orderly shutdown of the executor service
        executor.awaitTermination(1,TimeUnit.SECONDS);
    }
    
}
