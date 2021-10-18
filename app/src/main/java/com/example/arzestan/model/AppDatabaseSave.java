package com.example.arzestan.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.arzestan.RoomDao;

@Database(version = 2,entities = {ModelDatabase.class},exportSchema = false)
public abstract class AppDatabaseSave extends RoomDatabase {

    public static  AppDatabaseSave appDatabase;

    public static AppDatabaseSave getAppDatabase(Context context) {
        if (appDatabase==null){
            appDatabase= Room.databaseBuilder(context.getApplicationContext(),AppDatabaseSave.class,"db_coin_save")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return appDatabase;
    }
    public abstract RoomDaoSave dao();
}
