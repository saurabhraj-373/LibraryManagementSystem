package com.library.Synchronization;
import java.util.concurrent.Callable;
public class AdderTask implements Callable<Void>{
    private Counter counter;

    public AdderTask(Counter counter) {
        this.counter = counter;
    }
    @Override
    public Void call() throws Exception {
        for (int i = 0; i <= 100; i++) {
            synchronized (counter) {
                int x = counter.getX();
                x = x + i;
                counter.setX(x);
            }
        }
        return null;
    }
}
