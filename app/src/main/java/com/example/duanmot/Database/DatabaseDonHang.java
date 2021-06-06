package com.example.duanmot.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.duanmot.DAO.DAODonHang;
import com.example.duanmot.Entity.DonHang;

@Database(entities = DonHang.class, version = 2)
public abstract class DatabaseDonHang extends RoomDatabase {

    public abstract DAODonHang daoDonHang();

    private static final String DB_NAME = "donhang_db";

    private static DatabaseDonHang instance;

    public static synchronized DatabaseDonHang getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DatabaseDonHang.class, DB_NAME).
                    fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
