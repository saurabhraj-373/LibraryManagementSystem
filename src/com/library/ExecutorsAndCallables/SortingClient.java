package com.library.ExecutorsAndCallables;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class SortingClient {
    public static void main(String[] args) throws Exception {
        // DEADLOCK if use fixed thread pool for recursive tasks
        // ExecutorService executorService = Executors.newFixedThreadPool(5); 
        ExecutorService executorService = Executors.newCachedThreadPool(); 
        List<Integer> list = List.of(5, 3, 8, 4, 2);
        
        System.out.println("Unsorted List: " + list);
        
        MergeSortTask mergeSortTask = new MergeSortTask(list, executorService);

        try {
            List<Integer> sortedList = executorService.submit(mergeSortTask).get();
            System.out.println("Sorted List: " + sortedList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        }
    }
}
