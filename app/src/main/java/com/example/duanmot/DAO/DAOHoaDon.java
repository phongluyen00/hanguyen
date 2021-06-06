package com.example.duanmot.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.duanmot.Entity.HoaDon;

import java.util.List;

@Dao
public interface DAOHoaDon {

    @Query("Select * from hoadon_db")
    List<HoaDon> HOA_DON_LIST();

    @Insert
    void InsertHoaDon(HoaDon hoaDon);

    @Update
    void UpdateHoaDon(HoaDon hoaDon);

}
