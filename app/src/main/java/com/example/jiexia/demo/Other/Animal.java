package com.example.jiexia.demo.Other;

public class Animal {

    private String aName;
    private String aSpeak;
    private int aIcon;

    public Animal() {
    }

    public Animal(String aName, String aSpeak, int aIcon) {
        this.aName = aName;
        this.aSpeak = aSpeak;
        this.aIcon = aIcon;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getaSpeak() {
        return aSpeak;
    }

    public void setaSpeak(String aSpeak) {
        this.aSpeak = aSpeak;
    }

    public int getAIcon() {
        return aIcon;
    }

    public void setAIcon(int aIcon) {
        this.aIcon = aIcon;
    }
}
