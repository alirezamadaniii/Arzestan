package com.example.arzestan.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.Menu;
import android.widget.PopupMenu;

import com.example.arzestan.R;


import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {

    NavController controller;
    NavHostFragment navHostFragment;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            SetUpNavigation();
//
    }


    private void SetUpNavigation() {
        navHostFragment= (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        controller=navHostFragment.getNavController();
        SetUpSmoothNavigation();
    }

    private void SetUpSmoothNavigation() {
        PopupMenu popupMenu=new PopupMenu(this,null);
        popupMenu.inflate(R.menu.menu);
        Menu menu=popupMenu.getMenu();
        SmoothBottomBar bottomBar=findViewById(R.id.bottomNavigation);
        bottomBar.setupWithNavController(menu,controller);
    }


}


