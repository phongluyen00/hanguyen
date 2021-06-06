package com.example.duanmot.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "donhang_db")
public class DonHang {
    @PrimaryKey(autoGenerate = true)
    int Id;
    String tenMatHang;
    int soLuong;
    Double thanhTien;


    public DonHang(String tenMatHang, int soLuong, Double thanhTien) {
        this.tenMatHang = tenMatHang;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenMatHang() {
        return tenMatHang;
    }

    public void setTenMatHang(String tenMatHang) {
        this.tenMatHang = tenMatHang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(Double thanhTien) {
        this.thanhTien = thanhTien;
    }
}
