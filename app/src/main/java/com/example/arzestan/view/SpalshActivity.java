package com.example.arzestan.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arzestan.viewmodel.AppViewModelFactory;
import com.example.arzestan.R;

import com.example.arzestan.RoomDao;
import com.example.arzestan.model.AllMarketModel;
import com.example.arzestan.model.AppDatabase;
import com.example.arzestan.model.MarketEntity;
import com.example.arzestan.viewmodel.AppViewModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class SpalshActivity extends AppCompatActivity {

    ImageView imageView1,
              imageView2,
              imageView3,
              imageView4,
              imageView5,
              imageView6;

    TextView textViewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        SetUpView();
        SetUpAnimation();
        setUpApiAndInsertTotabale();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                registerReceiver(broadcastReceiver,new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                if (isConnected()){
                    startActivity(new Intent(SpalshActivity.this,MainActivity.class));
                }else {
                    Toast.makeText(SpalshActivity.this, "خطا دراتصال به اینترنت", Toast.LENGTH_SHORT).show();
                }

            }
        },3000);


    }

    private void setUpApiAndInsertTotabale() {

        AppViewModel viewModel= new ViewModelProvider(this,new AppViewModelFactory()).get(AppViewModel.class);
        Observable.interval(20, TimeUnit.SECONDS)
                .flatMap(n->viewModel.marketListFutureCall().get())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AllMarketModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull AllMarketModel allMarketModel) {

                        RoomDao roomDao= AppDatabase.getAppDatabase(SpalshActivity.this).dao();

                        Completable.fromAction(()->roomDao.addToTable(new MarketEntity(allMarketModel)))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new CompletableObserver() {
                                    @Override
                                    public void onSubscribe(@NonNull Disposable d) {

                                    }

                                    @Override
                                    public void onComplete() {
                                        Log.i("TAG", "onResponse: ok");
                                    }

                                    @Override
                                    public void onError(@NonNull Throwable e) {
                                        Log.i("TAG", "onError: "+e.toString());
                                    }
                                });
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("TAG", "onError: "+e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void SetUpAnimation() {

        textViewTitle.setScaleX(0);
        textViewTitle.setScaleY(0);
        textViewTitle.animate().scaleX(1).scaleY(1).setDuration(1000).start();


        TranslateAnimation animation=new TranslateAnimation(0,240,0,170);
        animation.setDuration(1000);
        animation.start();
        animation.setRepeatCount(Animation.INFINITE);
        imageView1.setAnimation(animation);

        TranslateAnimation animation2=new TranslateAnimation(0,0,0,240);
        animation2.setDuration(1000);
        animation2.setRepeatCount(Animation.INFINITE);
        animation2.start();
        imageView2.setAnimation(animation2);

        TranslateAnimation animation3=new TranslateAnimation(0,-240,0,170);
        animation3.setDuration(1000);
        animation3.setRepeatCount(Animation.INFINITE);
        animation3.start();
        imageView3.setAnimation(animation3);


        TranslateAnimation animation4=new TranslateAnimation(0,-240,0,-170);
        animation4.setDuration(1000);
        animation4.setRepeatCount(Animation.INFINITE);
        animation4.start();
        imageView4.setAnimation(animation4);


        TranslateAnimation animation5=new TranslateAnimation(0,0,0,-240);
        animation5.setDuration(1000);
        animation5.setRepeatCount(Animation.INFINITE);
        animation5.start();
        imageView5.setAnimation(animation5);


        TranslateAnimation animation6=new TranslateAnimation(0,240,0,-170);
        animation6.setDuration(1000);
        animation6.setRepeatCount(Animation.INFINITE);
        animation6.start();
        imageView6.setAnimation(animation6);

    }

    private void SetUpView() {
        imageView1=findViewById(R.id.imageView2);
        imageView2=findViewById(R.id.imageView9);
        imageView3=findViewById(R.id.imageView11);
        imageView4=findViewById(R.id.imageView12);
        imageView5=findViewById(R.id.imageView10);
        imageView6=findViewById(R.id.imageView8);
        textViewTitle=findViewById(R.id.textView);
    }


    BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (isConnected()){
                startActivity(new Intent(SpalshActivity.this,MainActivity.class));
            }

        }
    };

    public boolean isConnected() {
        ConnectivityManager connectivityManager= (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        return networkInfo!=null && networkInfo.isConnected();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
}
