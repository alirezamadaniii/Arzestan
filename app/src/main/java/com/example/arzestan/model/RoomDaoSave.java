package com.example.arzestan.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RoomDaoSave {

    @Query("SELECT * FROM save")
    List<ModelDatabase> listDatabase();

    @Insert
    void addDatabase(ModelDatabase modelDatabase);
}
