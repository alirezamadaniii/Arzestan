package com.example.arzestan.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.arzestan.RoomDao;
import com.example.arzestan.model.AllMarketModel;
import com.example.arzestan.model.MarketEntity;
import com.example.arzestan.model.RetrofitApi;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AppViewModel extends ViewModel {



    RoomDao roomDao;
    RetrofitApi retrofitApi;
    public AppViewModel(RetrofitApi retrofitApi) {
        this.retrofitApi=retrofitApi;


    }

    public Future<Observable<AllMarketModel>> marketListFutureCall(){
        final ExecutorService executor = Executors.newSingleThreadExecutor();

        final Callable<Observable<AllMarketModel>> myNetworkCallable = new Callable<Observable<AllMarketModel>>() {
            @Override
            public Observable<AllMarketModel> call() throws Exception {
                return retrofitApi.list();
            }
        };

        final Future<Observable<AllMarketModel>> futureObservable = new Future<Observable<AllMarketModel>>() {
            @Override
            public boolean cancel(boolean b) {
                if (b){
                    executor.shutdown();
                }
                return false;
            }

            @Override
            public boolean isCancelled() {
                return executor.isShutdown();
            }

            @Override
            public boolean isDone() {
                return executor.isTerminated();
            }

            @Override
            public Observable<AllMarketModel> get() throws ExecutionException, InterruptedException {
                return executor.submit(myNetworkCallable).get();
            }

            @Override
            public Observable<AllMarketModel> get(long timeout, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
                return executor.submit(myNetworkCallable).get(timeout,timeUnit);
            }
        };

        return futureObservable;

    }

    public Future<Observable<AllMarketModel>> MarketFutureCall(){
        return marketListFutureCall();
    }

    public Flowable<MarketEntity> getAllMarketData(){
        return roomDao.listResult();
    }


}
