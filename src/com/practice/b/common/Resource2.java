package com.practice.b.common;

import java.util.ArrayList;
import java.util.List;


public class Resource2 extends Resource {
    private final List<String> allSequences;
    private Sequence sequence;

    public Resource2(String path, List<String> sequences) {
        super(path);
        this.path = path;
        this.allSequences = sequences;
        this.flag = false;
        this.sequence = new Sequence("", new ArrayList<>());
    }

    public Sequence getSequence() {
        return sequence;
    }

    public String getAllSequences(String pattern) {
        StringBuffer sb = new StringBuffer();
        sb.append("Accourding to pattern '" + pattern + "' it was found next sequences:\n");
        for (String s : this.allSequences) {
            sb.append("->{'" + s + "'}\n");
        }
        return sb.toString();
    }

    public void addSequence(String key) {
        if (!this.allSequences.contains(key)) {
            this.allSequences.add(key);
            this.redefineSequence(key);
        }
    }

    private void redefineSequence(String key) {
        if (this.sequence.getLength() < key.length()) {
            this.sequence.setSequence(key);
        }
    }

    public void addIndex(int index) {
        this.sequence.addIndex(index);
    }

    public void increaseSequenceNumber() {
        this.sequence.increaseOccurence();
    }

    public Sequence getLongestSequence() {
        return this.sequence;
    }

    public void reset() {
        this.allSequences.clear();
        this.sequence = new Sequence("", new ArrayList<>());
        this.flag = false;
    }

}
