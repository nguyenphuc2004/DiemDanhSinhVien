package com.example.myapplication.DanhSachTatCaSinhVien;

import java.io.Serializable;

public class SinhVien implements Serializable {
    private int Mssv;
    private String hoVaTen;
    private String lopHoc;
    private String gioiTinh;
    private String namSinh;
    private String soDienThoai;
    private String gmail;

    public SinhVien(int mssv, String hoVaTen, String lopHoc,String gioiTinh,String ngaySinh, String soDienThoai, String gmail) {
        Mssv = mssv;
        this.hoVaTen = hoVaTen;
        this.lopHoc = lopHoc;
        this.gioiTinh = gioiTinh;
        this.namSinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.gmail = gmail;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getMssv() {
        return Mssv;
    }

    public void setMssv(int mssv) {
        Mssv = mssv;
    }

    public String getLopHoc() {
        return lopHoc;
    }

    public void setLopHoc(String lopHoc) {
        this.lopHoc = lopHoc;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
}
