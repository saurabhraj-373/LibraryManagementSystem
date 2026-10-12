package com.library.ProcessesAndThreads;

public class NumberPrinter implements Runnable{
    public void print() throws InterruptedException{
        System.out.println("Hello from NumberPrinter.");
        System.out.println("Thread: "+Thread.currentThread().getName());
        
        for(int i=1;i<=10;i++){
            Thread.sleep(100);
            System.out.println(i);
        }
    }

    @Override 
    public void run() {
        try {
            print();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
