package com.example.arzestan.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.arzestan.view.FragmentLose;
import com.example.arzestan.view.FragmentTop;

public class PagerAdapter extends FragmentPagerAdapter {


    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        Fragment fragment=null;
            if (i==0){
                fragment=new FragmentTop();
            }else if (i==1){
                fragment=new FragmentLose();
            }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Top";


            case 1:
                return "lose";

        }
        return "";
    }
}
