package com.practice.a.common;

import java.util.Set;

/**
 * Stores data and provides methods to work with it
 */
public class Resource {
    private final Set<Integer> commonSet;

    public Resource(Set<Integer> commonSet) {
        this.commonSet = commonSet;
    }

    public synchronized boolean isExist(Integer number) {
        return this.commonSet.contains(number);
    }

    public synchronized void addToSet(Integer number) {
        this.commonSet.add(number);
    }

    public void addIndividualSet(Set<Integer> individualSet) {
        this.commonSet.addAll(individualSet);
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Resource { commonSet = [ ");
        String set = this.commonSet.toString();
        int i = 0;
        for (Integer integer : this.commonSet) {
            stringBuffer.append(integer + ", ");
            if (i == 40) {
                stringBuffer.append("\n");
                i = 0;
            }
            i++;
        }
        stringBuffer.append(" ]\n         }");
        return stringBuffer.toString();
    }
}
