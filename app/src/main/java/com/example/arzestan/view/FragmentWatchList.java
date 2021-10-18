package com.example.arzestan.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arzestan.R;
import com.example.arzestan.RoomDao;
import com.example.arzestan.adapter.AdapterSaveDatabase;
import com.example.arzestan.model.AppDatabaseSave;
import com.example.arzestan.model.RoomDaoSave;

public class FragmentWatchList extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_watch_list, container, false);

        RecyclerView recyclerView=view.findViewById(R.id.recy_whatcList);
        RoomDaoSave daoSave= AppDatabaseSave.getAppDatabase(getContext()).dao();
        AdapterSaveDatabase adapterSaveDatabase=new AdapterSaveDatabase(daoSave.listDatabase(),getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(adapterSaveDatabase);

        return view;
    }
}