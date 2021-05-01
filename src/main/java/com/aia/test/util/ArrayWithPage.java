package com.aia.test.util;

import java.util.ArrayList;

public class ArrayWithPage {
    int totalPage;

    public ArrayWithPage(int totalPage, int currentPage, ArrayList list) {
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.list = list;
    }

    int currentPage;
    ArrayList list;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public ArrayList getList() {
        return list;
    }

    public void setList(ArrayList list) {
        this.list = list;
    }
}
