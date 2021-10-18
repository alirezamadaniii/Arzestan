package com.example.arzestan.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllMarketModel {

    @SerializedName("data")
    private RootData data;

    @SerializedName("status")
    private ListStatus listStatus;

    public RootData getRootData(){
        return data;
    }

    public ListStatus getStatus(){
        return listStatus;
    }





    public static class ListStatus {

        @SerializedName("error_message")
        private String errorMessage;

        @SerializedName("elapsed")
        private int elapsed;

//        @SerializedName("total_count")
//        private int totalCount;
//
        @SerializedName("credit_count")
        private int creditCount;

        @SerializedName("error_code")
        private String errorCode;

        @SerializedName("timestamp")
        private String timestamp;

//        @SerializedName("notice")
//        private Object notice;

        public String getErrorMessage(){
            return errorMessage;
        }

        public int getElapsed(){
            return elapsed;
        }

//        public int getTotalCount(){
//            return totalCount;
//        }
//
        public int getCreditCount(){
            return creditCount;
        }

        public String getErrorCode(){
            return errorCode;
        }

        public String getTimestamp(){
            return timestamp;
        }

//        public Object getNotice(){
//            return notice;
//        }
    }


    public static class RootData{

        @SerializedName("cryptoCurrencyList")
        private List<DataItem> cryptoCurrencyList;

        @SerializedName("totalCount")
        private String totalCount;

        public List<DataItem> getCryptoCurrencyList() {
            return cryptoCurrencyList;
        }

        public String getTotalCount() {
            return totalCount;
        }
    }
}