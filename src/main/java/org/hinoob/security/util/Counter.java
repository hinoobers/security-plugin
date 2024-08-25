package org.hinoob.security.util;

public class Counter {

    private long start;
    private long millis;

    private int count;

    public Counter(long millis) {
        this.millis = millis;
        this.start = System.currentTimeMillis();
    }

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public void reset() {
        count = 0;
        start = System.currentTimeMillis();
    }

    public boolean hasReached() {
        return System.currentTimeMillis() - start >= millis;
    }
}
