package com.example.myapplication.DanhSachLop;

import java.io.Serializable;

public class ListClass implements Serializable {
    private String tenLop;

    public ListClass(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }
}
