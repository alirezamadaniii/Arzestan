package com.example.arzestan.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arzestan.R;
import com.example.arzestan.RoomDao;
import com.example.arzestan.adapter.AdapterLose;
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


public class FragmentLose extends Fragment {
    List<DataItem> data;
    AdapterLose gainLoseAdapter;
    View view;
    Disposable disposable;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_lose, container, false);
        GetDataFromServer(view);



        return view;
    }

    private void GetDataFromServer(View view) {

        RecyclerView recyclerView=view.findViewById(R.id.recy_lose);
        RoomDao dao= AppDatabase.getAppDatabase(getContext()).dao();
        disposable= dao.listResult().subscribeOn(Schedulers.io())
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
                            dataItems.add(data.get(i));
                        }


                        if (recyclerView.getAdapter() == null){
                            gainLoseAdapter=new AdapterLose(dataItems,getContext());
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
                            recyclerView.setAdapter(gainLoseAdapter);
                        }else {
                            gainLoseAdapter = (AdapterLose) recyclerView.getAdapter();
                            gainLoseAdapter.updateData(dataItems);
                        }




                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();

    }
}