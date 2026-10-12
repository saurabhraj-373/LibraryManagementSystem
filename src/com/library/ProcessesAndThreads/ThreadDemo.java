package com.library.ProcessesAndThreads;

public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello from main");
        System.out.println("Thread: "+Thread.currentThread().getName());
        
        // NumberPrinter printer = new NumberPrinter();
        // Thread t = new Thread(printer);
        // t.start();

        for(int i=1;i<=100;i++){
            IndividualNumberPrinter printer = new IndividualNumberPrinter(i);
            Thread t = new Thread(printer);
            
            //t.run(); //func call using obj, no thread

            t.start(); //start a new thread
        }

        System.out.println("Hello Again from main");
        System.out.println("Thread: "+Thread.currentThread().getName());

    }
}
    