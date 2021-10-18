package com.example.arzestan.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.arzestan.R;
import com.example.arzestan.adapter.PagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class FragmentHome extends Fragment {

    ViewPager viewPager;
    TabLayout tabLayout;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view= inflater.inflate(R.layout.fragment_home, container, false);


        tabLayout=view.findViewById(R.id.tabLayout);
        viewPager=view.findViewById(R.id.view_pager_fr_home);
        PagerAdapter adapter=new PagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);







        return view;
    }



}