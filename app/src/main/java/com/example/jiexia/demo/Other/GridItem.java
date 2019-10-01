package com.example.jiexia.demo.Other;

/**
 * grid网格布局的item对象
 * */
public class GridItem {

    private int iId;
    private String iName;

    public GridItem(int iId, String iName) {
        this.iId = iId;
        this.iName = iName;
    }

    public int getiId() {
        return iId;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }
}
