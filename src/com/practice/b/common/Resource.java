package com.practice.b.common;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Keeps all found sequences, gives methods for processing them
 */
public class Resource {
    protected File[] files;
    protected String activeFileName;
    protected String path;
    protected String data;
    private Map<String, Sequence> sequences;
    private Set<Sequence> trend; // for determination which is longest
    protected boolean flag; // is finished process of data

    public Resource(String path) {
        this.path = path;
    }

    public Resource(String path, Map<String, Sequence> sequences, Set<Sequence> trend) {
        this.path = path;
        this.sequences = sequences;
        this.trend = trend;
        this.flag = false;
    }

    public Map<String, Sequence> getSequences() {
        return sequences;
    }

    public boolean isFlag() {
        return flag;
    }

    public String getPath() {
        return path;
    }

    public String getActiveFileName() {
        return activeFileName;
    }

    public String getData() {
        return data;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setData(String data) {
        this.data = data;
    }


    public void setFiles(File[] files) {
        this.files = files;
    }

    public void setActiveFileName(String activeFileName) {
        this.activeFileName = activeFileName;
    }

    public void addSequence(String key) {
        if (!this.sequences.containsKey(key)) {
            this.sequences.put(key, new Sequence(key, new ArrayList<>()));
            this.trend.add(this.sequences.get(key));
        }
    }

    public void addIndex(String key, int index) {
        this.sequences.get(key).addIndex(index);
    }

    public void increaseSequenceNumber(String key) {
        this.sequences.get(key).increaseOccurence();
    }

    public Sequence getLongestSequence() {
        if (this.trend.isEmpty()) {
            return null;
        }
        return new ArrayList<Sequence>(this.trend).get(0);
    }

    public void reset() {
        this.sequences.clear();
        this.trend.clear();
        this.flag = false;
    }

}
