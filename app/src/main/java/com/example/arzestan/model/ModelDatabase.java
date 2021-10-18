package com.example.arzestan.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "save")
public class ModelDatabase {
    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    private int idImage;
    private String name;
    private String price;
    private String change;
    private String symbol;
    private String logo;
    private String change24;
    private String marketCap;
    private String high24;
    private String low24;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getChange24() {
        return change24;
    }

    public void setChange24(String change24) {
        this.change24 = change24;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    public String getHigh24() {
        return high24;
    }

    public void setHigh24(String high24) {
        this.high24 = high24;
    }

    public String getLow24() {
        return low24;
    }

    public void setLow24(String low24) {
        this.low24 = low24;
    }
}
