package com.story.mipsa.pocketify;

/**
 * Created by Mipsa on 6/11/2019.
 */

public class itemExample {

    public String subjectName;
    public int present,absent,total,bunk,attend;
    float percentage;

    public itemExample(String subjectName, int present, int absent, int total,float percentage, int bunk, int attend) {
        this.subjectName = subjectName;
        this.present = present;
        this.absent = absent;
        this.total = total;
        this.percentage = percentage;
        this.bunk = bunk;
        this.attend = attend;
    }

    public int getBunk() {
        return bunk;
    }

    public int getAttend() {
        return attend;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getPresent() {
        return present;
    }

    public int getAbsent() {
        return absent;
    }

    public float getPercentage() {
        return percentage;
    }

    public int getTotal() {
        return total;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setPresent(int present) {
        this.present = present;
    }

    public void setAbsent(int absent) {
        this.absent = absent;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setBunk(int bunk) {
        this.bunk = bunk;
    }

    public void setAttend(int attend) {
        this.attend = attend;
    }
}
