package com.example.duanmot.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "loaisanpham_db")
public class LoaiSanPham {
    @PrimaryKey(autoGenerate = true)
    public int iD;
    @ColumnInfo(name = "loaiSanPham")
    public String loaiSanPham;


    public LoaiSanPham(String loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    public LoaiSanPham() {
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(String loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }
}
