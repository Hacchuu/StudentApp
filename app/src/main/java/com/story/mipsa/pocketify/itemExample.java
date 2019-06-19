package com.story.mipsa.pocketify;

/**
 * Created by Mipsa on 6/11/2019.
 */

public class itemExample {

    public String subjectName;
    public int present,absent,total,percentage;

    public itemExample(String subjectName, int present, int absent, int percentage, int total) {
        this.subjectName = subjectName;
        this.present = present;
        this.absent = absent;
        this.percentage = percentage;
        this.total = total;
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

    public int getPercentage() {
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

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
