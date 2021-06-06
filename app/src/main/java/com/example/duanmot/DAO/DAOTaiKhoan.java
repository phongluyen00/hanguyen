package com.example.duanmot.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.duanmot.Entity.TaiKhoan;

import java.util.List;

@Dao
public interface DAOTaiKhoan {

    @Query("Select * from taikhoan_db where tenTaiKhoan=(:tenTaiKhoan) and  matKhau=(:matKhau)")
    TaiKhoan dangnhap(String tenTaiKhoan, String matKhau);

    @Query("Select * from taikhoan_db")
    List<TaiKhoan> TAI_KHOAN_LIST();

    @Insert
    void InsertTaiKhoan(TaiKhoan taiKhoan);

    @Delete
    void DeleteTaiKhoan(TaiKhoan taiKhoan);

    @Update
    void UpdateTaiKhoan(TaiKhoan taiKhoan);
}
