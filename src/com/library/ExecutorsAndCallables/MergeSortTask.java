package com.library.ExecutorsAndCallables;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.List;

public class MergeSortTask implements Callable<List<Integer>>{

    private final List<Integer> list;
    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    public MergeSortTask(List<Integer> list, ExecutorService executorService) {
        this.list = list;
        this.executorService = executorService;
    }
    
    @Override
    public List<Integer> call() throws Exception {
        if(list.size() == 1){
            return list;
        }

        int mid = list.size() >> 1;

        List<Integer> leftHalf = list.subList(0, mid); // 0 -> mid-1
        List<Integer> rightHalf = list.subList(mid, list.size()); // mid -> size-1
        
        MergeSortTask leftTask = new MergeSortTask(leftHalf, executorService);
        MergeSortTask rightTask = new MergeSortTask(rightHalf, executorService);

        Future<List<Integer>> leftFuture = executorService.submit(leftTask);
        Future<List<Integer>> rightFuture = executorService.submit(rightTask);

        //Blocking calls - will wait for the sorting of both halves to complete before merging
        List<Integer> sortedLeft = leftFuture.get();
        List<Integer> sortedRight = rightFuture.get();

        return merge(sortedLeft, sortedRight);

    }

    private List<Integer> merge(List<Integer> sortedLeft, List<Integer> sortedRight) {
        int leftIndex = 0, rightIndex = 0;
        List<Integer> mergedList = new java.util.ArrayList<>();

        while(leftIndex < sortedLeft.size() && rightIndex < sortedRight.size()) {
            if(sortedLeft.get(leftIndex) <= sortedRight.get(rightIndex)) {
                mergedList.add(sortedLeft.get(leftIndex++));
            } else {
                mergedList.add(sortedRight.get(rightIndex++));
            }
        }

        while(leftIndex < sortedLeft.size()) {
            mergedList.add(sortedLeft.get(leftIndex++));
        }

        while(rightIndex < sortedRight.size()) {
            mergedList.add(sortedRight.get(rightIndex++));
        }

        return mergedList;
    }
    
}
