package com.example.arzestan.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.arzestan.model.RetrofitApi;
import com.example.arzestan.viewmodel.AppViewModel;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppViewModelFactory implements ViewModelProvider.Factory {
    RetrofitApi retrofitApi;
    public AppViewModelFactory() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.coinmarketcap.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        retrofitApi=retrofit.create(RetrofitApi.class);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AppViewModel(retrofitApi);
    }
}
