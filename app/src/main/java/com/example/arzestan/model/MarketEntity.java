package com.example.arzestan.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MarketEntity {

    @PrimaryKey
    public int idTable;

    public AllMarketModel allMarketModel;

    public int getIdTable() {
        return idTable;
    }

    public AllMarketModel getAllMarketModel() {
        return allMarketModel;
    }

    public MarketEntity(AllMarketModel allMarketModel) {
        this.allMarketModel = allMarketModel;
    }
}
