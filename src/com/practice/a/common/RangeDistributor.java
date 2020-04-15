package com.practice.a.common;

import java.util.Arrays;

/**
 * Distibutes ranges
 */
public class RangeDistributor {
    private final int startRange;
    private final int endRange;
    private final int[][] rangesForThreads;
    private final int n; // number of threads

    public RangeDistributor(Console console) {
        this.startRange = console.getStartRange();
        this.endRange = console.getEndRange();
        this.n = console.getN();
        this.rangesForThreads = new int[n][2];
        this.distributeRange();
    }

    private void distributeRange() {
        int interval = (this.endRange - this.startRange) / this.n;
        int number = this.startRange;
        for (int i = 0; i < this.n; i++) {
            this.rangesForThreads[i][0] = number;
            number += interval;
            if (number >= this.endRange || number == this.endRange - 1) {
                number = this.endRange;
            }
            this.rangesForThreads[i][1] = number;
            if (number < this.endRange) {
                number++;
            }
        }
    }

    public int getN() {
        return n;
    }

    public int getStartRangeForThread(int threadId) {
        return this.rangesForThreads[threadId][0];
    }

    public int getEndRangeForThread(int threadId) {
        return this.rangesForThreads[threadId][1];
    }

    @Override
    public String toString() {
        return "RangeDistributor{" +
                "startRange=" + startRange +
                ", endRange=" + endRange +
                ", rangesForThreads=" + Arrays.toString(this.rangesForThreads[0]) +
                ", \nn=" + n +
                " \nanother " + Arrays.toString(rangesForThreads[1]) +
                " \nanother " + Arrays.toString(rangesForThreads[2]) +
                '}';

    }
}
