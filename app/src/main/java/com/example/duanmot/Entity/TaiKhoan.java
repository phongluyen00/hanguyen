package com.example.duanmot.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "taikhoan_db")
public class TaiKhoan {

    @PrimaryKey(autoGenerate = true)
    public int iD;
    @ColumnInfo(name = "tenTaiKhoan")
    public String tenTaiKhoan;
    @ColumnInfo(name = "matKhau")
    public String matKhau;
    @ColumnInfo(name = "soDienThoai")
    public String soDienThoai;
    @ColumnInfo(name = "email")
    public String eMail;


    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
}