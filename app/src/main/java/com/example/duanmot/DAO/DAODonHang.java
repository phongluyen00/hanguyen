package com.example.duanmot.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.duanmot.Entity.DonHang;

import java.util.List;

@Dao
public interface DAODonHang {

    @Query("Select * from donhang_db")
    List<DonHang> DON_HANG_LIST();

    @Query("Delete  from donhang_db")
    void DeleteTable();

    @Insert
    void InsertDonHang(DonHang donHang);

    @Delete
    void Delete(DonHang donHang);
}
