package com.example.arzestan.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ModelConverter {

    @TypeConverter
    public String toJson(AllMarketModel allMarketModel){
        if (allMarketModel==null){
            return (null);
        }
        Gson gson=new Gson();
        Type type=new TypeToken<AllMarketModel>(){}.getType();
        String  json=gson.toJson(allMarketModel,type);
        return json;
    }

    @TypeConverter
    public AllMarketModel toDataClass(String allmarket){
        if (allmarket==null){
            return (null);
        }
        Gson gson=new Gson();
        Type type=new TypeToken<AllMarketModel>(){}.getType();
        AllMarketModel allMarketModel=gson.fromJson(allmarket,type);
        return allMarketModel;
    }
}
