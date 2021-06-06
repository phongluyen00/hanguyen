package com.example.duanmot.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sanpham_db")
public class SanPham {
    @PrimaryKey(autoGenerate = true)
    public int iD;
    @ColumnInfo(name = "tenSanPham")
    private String tenSanPham;
    @ColumnInfo(name = "loaiSanPham")
    private String loaiSanPham;
    @ColumnInfo(name = "giaSanPham")
    private double giaSanPham;
    @ColumnInfo(name = "soLuongSanPham")
    private int soLuongSanPham;

    public SanPham(String tenSanPham, String loaiSanPham, double giaSanPham, int soLuongSanPham) {
        this.tenSanPham = tenSanPham;
        this.loaiSanPham = loaiSanPham;
        this.giaSanPham = giaSanPham;
        this.soLuongSanPham = soLuongSanPham;
    }


    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(String loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    public double getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(double giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public int getSoLuongSanPham() {
        return soLuongSanPham;
    }

    public void setSoLuongSanPham(int soLuongSanPham) {
        this.soLuongSanPham = soLuongSanPham;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }


}
