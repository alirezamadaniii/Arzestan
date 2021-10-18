package com.example.arzestan;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.arzestan.model.AllMarketModel;
import com.example.arzestan.model.DataItem;
import com.example.arzestan.model.MarketEntity;
import com.example.arzestan.model.ModelDatabase;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addToTable(MarketEntity marketEntity);

    @Query("SELECT * FROM MarketEntity")
    Flowable<MarketEntity> listResult();


}
