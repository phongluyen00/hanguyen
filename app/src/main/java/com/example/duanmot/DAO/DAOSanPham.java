package com.example.duanmot.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.duanmot.Entity.SanPham;

import java.util.List;

@Dao
public interface DAOSanPham {

    @Query("Select * from sanpham_db")
    List<SanPham> SANPHAM_LIST();

    @Query("select * from sanpham_db where tenSanPham = :tenSanPham")
    SanPham getSanPham(String tenSanPham);

    @Insert
    void InsertSanPham(SanPham sanPham);

    @Delete
    void DeleteSanPham(SanPham sanPham);

    @Update
    void UpdataSanpham(SanPham sanPham);
}
