package org.hinoob.security.util;

public class Counter {

    private long start;
    private boolean started;
    private int count;

    public Counter() {

    }

    public void start() {
        if(!started) {
            start = System.currentTimeMillis();
            started = true;
        }
    }


    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public long getStart() {
        return start;
    }

    public void reset() {
        count = 0;
        start = System.currentTimeMillis();
    }

    public boolean hasReached(long millis) {
        return System.currentTimeMillis() - start >= millis;
    }
}