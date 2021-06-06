package com.example.duanmot.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.duanmot.Entity.LoaiSanPham;

import java.util.List;

@Dao
public interface DAOLoaiSanPham {


    @Query("Select * from loaisanpham_db")
    List<LoaiSanPham> loaiSanPham_LIST();

    @Query("SELECT * FROM loaisanpham_db where loaiSanPham = :loaiSanPham")
    LoaiSanPham getLoaiSanPham(String loaiSanPham);

    @Insert
    void InsertLoaiSanpham(LoaiSanPham loaiSanPham);

    @Delete
    void DeleteLoaiSanpham(LoaiSanPham loaiSanPham);

    @Update
    void UpdateLoaiSanpham(LoaiSanPham loaiSanPham);

}
