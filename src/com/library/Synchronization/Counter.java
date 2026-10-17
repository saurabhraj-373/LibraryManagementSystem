package com.library.Synchronization;

public class Counter {
    private int x;

    public Counter(int x) {
        this.x = x;
    }

    public synchronized int getX() {
        return x;
    }

    public synchronized void setX(int x) {
        this.x = x;
    }
}
