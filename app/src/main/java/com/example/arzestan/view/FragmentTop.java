package com.example.arzestan.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.arzestan.R;
import com.example.arzestan.RoomDao;
import com.example.arzestan.adapter.AdaptetTop;
import com.example.arzestan.adapter.GainLoseAdapter;
import com.example.arzestan.model.AllMarketModel;
import com.example.arzestan.model.AppDatabase;
import com.example.arzestan.model.DataItem;
import com.example.arzestan.model.MarketEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class FragmentTop extends Fragment {
    List<DataItem> data;
    AdaptetTop gainLoseAdapter;
    Disposable disposable;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_top, container, false);






        RecyclerView  recyclerView=view.findViewById(R.id.recy_top);
        RoomDao dao= AppDatabase.getAppDatabase(getContext()).dao();
        disposable=dao.listResult().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MarketEntity>() {
                    @Override
                    public void accept(MarketEntity marketEntity) throws Throwable {
                        AllMarketModel allMarketModel = marketEntity.getAllMarketModel();
                        data = allMarketModel.getRootData().getCryptoCurrencyList();

                        // sort Model list by change percent (lowest to highest)
                        Collections.sort(data, new Comparator<DataItem>() {
                            @Override
                            public int compare(DataItem o1, DataItem o2) {
                                return Integer.valueOf((int) o1.getListQuote().get(0).getPercentChange24h()).compareTo((int) o2.getListQuote().get(0).getPercentChange24h());
                            }
                        });

                            ArrayList<DataItem> dataItems = new ArrayList<>();
                            //if page was top Gainers
                                for (int i = 0;i < 10;i++) {
                                    dataItems.add(data.get(data.size() - 1 - i));
                                }




                        if (recyclerView.getAdapter() == null){
                            gainLoseAdapter=new AdaptetTop(dataItems,getContext());
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
                            recyclerView.setAdapter(gainLoseAdapter);
                        }else {
                            gainLoseAdapter = (AdaptetTop) recyclerView.getAdapter();
                            gainLoseAdapter.updateData(dataItems);
                        }


                    }
                });


        return view;


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}