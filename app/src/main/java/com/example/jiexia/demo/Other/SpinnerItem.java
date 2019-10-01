package com.example.jiexia.demo.Other;

public class SpinnerItem {

    private int hIcon;
    private String hName;

    public SpinnerItem() {
    }

    public SpinnerItem(int hIcon, String hName) {
        this.hIcon = hIcon;
        this.hName = hName;
    }

    public int gethIcon() {
        return hIcon;
    }

    public String gethName() {
        return hName;
    }

    public void sethIcon(int hIcon) {
        this.hIcon = hIcon;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

}
