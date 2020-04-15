package com.practice.a.common;

/**
 * Fixes time
 */
public class StopWatch {
    private static long[] timestamp = new long[2];

    public static void makeZero() {
        timestamp = new long[2];
    }

    public static void fixStartTime() {
        timestamp[0] = System.currentTimeMillis();
    }

    public static synchronized void fixEndTime() {
        if (timestamp[1] < System.currentTimeMillis()) {
            timestamp[1] = System.currentTimeMillis();
        }
    }

    public static synchronized void showCurrentDuration() {
        System.out.println("Current duration is: " + (timestamp[1] - timestamp[0]) + " ms");
    }

    public static synchronized String getCurrentDuration() {
        return (timestamp[1] - timestamp[0]) + " ms";
    }

}
