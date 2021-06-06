package com.example.duanmot.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.duanmot.DAO.DAOLoaiSanPham;
import com.example.duanmot.Entity.LoaiSanPham;

@Database(entities = LoaiSanPham.class, version = 2, exportSchema = false)
public abstract class DatabaseLoaiSP extends RoomDatabase {

    public abstract DAOLoaiSanPham daoLoaiSanPham();

    private static final String DATABASE_NAME = "loaisanpham_db";
    private static DatabaseLoaiSP instance;

    public static synchronized DatabaseLoaiSP getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DatabaseLoaiSP.class
                    , DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
