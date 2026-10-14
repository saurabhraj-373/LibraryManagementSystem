package com.library.ExecutorsAndCallables;
public class IndividualNumberPrinter implements Runnable{
    private final int number;

        public IndividualNumberPrinter(int number) {
            this.number = number;
        }

    @Override 
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": " + number);
    }
}