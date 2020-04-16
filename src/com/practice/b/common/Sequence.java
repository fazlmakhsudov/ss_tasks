package com.practice.b.common;

import java.util.List;

public class Sequence implements Comparable<Sequence> {
    private String sequence;
    private int occurence;
    private final List<Integer> indexes;

    public Sequence(String sequence, List<Integer> indexes) {
        this.sequence = sequence;
        this.indexes = indexes;
        this.occurence = 0;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getSequence() {
        return sequence;
    }

    public void increaseOccurence() {
        this.occurence++;
    }

    public void addIndex(int index) {
        this.indexes.add(index);
    }

    public int getLength() {
        return this.sequence.length();
    }

    public int getOccurence() {
        return this.occurence;
    }


    @Override
    public int compareTo(Sequence o) {
        if (this.getLength() > o.getLength()) {
            return -1;
        } else if (this.getLength() < o.getLength()) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n\t{'" + this.sequence +
                "' } with occurence: " + this.occurence);
        stringBuffer.append("\n\t" + "indexe of first occurence: " + this.indexes.get(0));
        if (this.indexes.size() > 1) {
            stringBuffer.append("\n\t" + "indexe of second occurence: " + this.indexes.get(1));
        }
        return stringBuffer.toString();
    }
}
