package com.library.ExecutorsAndCallables;

import java.util.concurrent.Callable;

public class NumberMultiplier implements Callable<Integer> {
    private final int number;

    public NumberMultiplier(int number) {
        this.number = number;
    }

    @Override
    public Integer call() {
        int result = number * 5;
        //System.out.println("The square of " + number + " is " + result);
        return result;
    }
}
