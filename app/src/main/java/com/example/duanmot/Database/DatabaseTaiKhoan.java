package com.example.duanmot.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.duanmot.DAO.DAOTaiKhoan;
import com.example.duanmot.Entity.TaiKhoan;

@Database(entities = TaiKhoan.class, version = 4, exportSchema = false)
public abstract class DatabaseTaiKhoan extends RoomDatabase {
    private static final String DATABASE_NAME = "taikhoan_db";

    public abstract DAOTaiKhoan daoTaiKhoan();

    private static DatabaseTaiKhoan instance;

    public static synchronized DatabaseTaiKhoan getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DatabaseTaiKhoan.class
                    , DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
