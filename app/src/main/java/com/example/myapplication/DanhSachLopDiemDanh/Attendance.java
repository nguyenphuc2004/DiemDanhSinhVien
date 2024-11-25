package com.example.myapplication.DanhSachLopDiemDanh;

public class Attendance {
    private int id;
    private int mssv;
    private String hoTen;
    private String lopHoc;
    private String attendanceDate;
    private String status;

    public Attendance(int id, int mssv, String hoTen, String attendanceDate, String status, String lopHoc) {
        this.id = id;
        this.mssv = mssv;
        this.hoTen = hoTen;
        this.attendanceDate = attendanceDate;
        this.status = status;
        this.lopHoc = lopHoc;
    }

    public String getLopHoc() {
        return lopHoc;
    }

    public void setLopHoc(String lopHoc) {
        this.lopHoc = lopHoc;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMssv() {
        return mssv;
    }

    public void setMssv(int mssv) {
        this.mssv = mssv;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
