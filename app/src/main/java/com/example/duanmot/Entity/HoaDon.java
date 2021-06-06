package com.example.duanmot.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "hoadon_db")
public class HoaDon {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "ngayLap")
    public String ngayLap;
    @ColumnInfo(name = "tenKhachHang")
    public String tenKhachHang;
    @ColumnInfo(name = "diaChi")
    public String diaChi;
    @ColumnInfo(name = "soDienThoaiKh")
    public String soDienThoaiKh;

    @ColumnInfo(name = "tongTien")
    public double tongTien;

    public HoaDon() {
    }

    public HoaDon(String ngayLap, String tenKhachHang, String diaChi, String soDienThoaiKh, double tongTien) {
        this.ngayLap = ngayLap;
        this.tenKhachHang = tenKhachHang;
        this.diaChi = diaChi;
        this.soDienThoaiKh = soDienThoaiKh;

        this.tongTien = tongTien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoaiKh() {
        return soDienThoaiKh;
    }

    public void setSoDienThoaiKh(String soDienThoaiKh) {
        this.soDienThoaiKh = soDienThoaiKh;
    }


    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
}
