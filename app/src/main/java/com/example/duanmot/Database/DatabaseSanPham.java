package com.example.duanmot.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.duanmot.DAO.DAOSanPham;
import com.example.duanmot.Entity.SanPham;

@Database(entities = SanPham.class, version = 3, exportSchema = false)
public abstract class DatabaseSanPham extends RoomDatabase {

    public abstract DAOSanPham daoSanPham();

    private static final String DATABASE_NAME = "sanpham_db";
    private static DatabaseSanPham instance;

    public static synchronized DatabaseSanPham getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DatabaseSanPham.class
                    , DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
