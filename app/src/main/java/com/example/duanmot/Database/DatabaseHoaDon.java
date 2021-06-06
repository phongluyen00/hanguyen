package com.example.duanmot.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.duanmot.DAO.DAOHoaDon;
import com.example.duanmot.Entity.HoaDon;

@Database(entities = HoaDon.class, version = 2)
public abstract class DatabaseHoaDon extends RoomDatabase {

    public abstract DAOHoaDon daoHoaDon();

    private static final String DB_NAME = "hoadon_db";

    private static DatabaseHoaDon instance;

    public static synchronized DatabaseHoaDon getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DatabaseHoaDon.class, DB_NAME).
                    fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
