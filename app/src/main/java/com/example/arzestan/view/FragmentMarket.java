package com.example.arzestan.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arzestan.R;
import com.example.arzestan.RoomDao;
import com.example.arzestan.adapter.GainLoseAdapter;
import com.example.arzestan.model.AllMarketModel;
import com.example.arzestan.model.AppDatabase;
import com.example.arzestan.model.DataItem;
import com.example.arzestan.model.MarketEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FragmentMarket extends Fragment {

    GainLoseAdapter gainLoseAdapter;
    Disposable disposable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_market, container, false);
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
        String time=dateFormat.format(new Date());

        TextView textViewTime=view.findViewById(R.id.txt_time);

        textViewTime.setText(time);

        RoomDao roomDao= AppDatabase.getAppDatabase(getContext()).dao();
        disposable = roomDao.listResult().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MarketEntity>() {
                    @Override
                    public void accept(MarketEntity marketEntity) throws Throwable {
                        AllMarketModel allMarketModel=marketEntity.getAllMarketModel();
                        RecyclerView recyclerView=view.findViewById(R.id.recy_market);
                        ArrayList<DataItem>list=new ArrayList<>();
                        for (int i = 0; i <allMarketModel.getRootData().getCryptoCurrencyList().size() ; i++) {
                            list.add(allMarketModel.getRootData().getCryptoCurrencyList().get(i));
                        }


                        if (recyclerView.getAdapter() == null){
                            gainLoseAdapter=new GainLoseAdapter(list,getContext());
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
                            recyclerView.setAdapter(gainLoseAdapter);
                        }else {
                            gainLoseAdapter = (GainLoseAdapter) recyclerView.getAdapter();
                            gainLoseAdapter.updateData(list);
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