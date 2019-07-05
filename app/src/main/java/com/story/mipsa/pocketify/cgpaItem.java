package com.story.mipsa.pocketify;

/**
 * Created by Mipsa on 7/3/2019.
 */

public class cgpaItem {
    private String cgpaSubName,subGrade,subCredit;

    public cgpaItem(String cgpaSubName, String subGrade, String subCredit) {
        this.cgpaSubName = cgpaSubName;
        this.subGrade = subGrade;
        this.subCredit = subCredit;
    }

    public String getCgpaSubName() {
        return cgpaSubName;
    }

    public void setCgpaSubName(String cgpaSubName) {
        this.cgpaSubName = cgpaSubName;
    }

    public String getSubGrade() {
        return subGrade;
    }

    public void setSubGrade(String subGrade) {
        this.subGrade = subGrade;
    }

    public String getSubCredit() {
        return subCredit;
    }

    public void setSubCredit(String subCredit) {
        this.subCredit = subCredit;
    }
}
