package com.example.arzestan.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.arzestan.RoomDao;

@TypeConverters({ModelConverter.class})
@Database(version = 1,entities = {MarketEntity.class})
public abstract class AppDatabase extends RoomDatabase {
    public static  AppDatabase appDatabase;

    public static AppDatabase getAppDatabase(Context context) {
        if (appDatabase==null){
            appDatabase= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"db_coin")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return appDatabase;
    }
    public abstract RoomDao dao();
}
