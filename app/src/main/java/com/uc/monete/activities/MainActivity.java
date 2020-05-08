package com.uc.monete.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uc.monete.R;
import com.uc.monete.fragment.HistoryFragment;
import com.uc.monete.fragment.HomeFragment;
import com.uc.monete.fragment.SettingsFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    FloatingActionButton home;
    FloatingActionButton settings;
    FloatingActionButton history;
    Fragment fragment;
    BottomNavigationView bottomNavigationView;
    Button signout;

    public static final String FRAGMENT_TO_LOAD = "fragload";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.toolbar_main);
        toolbar.setTitle("Home");

        toolbar = findViewById(R.id.toolbar_main);
        toolbar.setTitle(R.string.menu_home);
        setSupportActionBar(toolbar);

        bottomNavigationView = findViewById(R.id.bottom_nav_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()){
                    case R.id.menu_home:
                        toolbar.setTitle(R.string.menu_home);
                        setSupportActionBar(toolbar);
                        fragment = new HomeFragment();
                        loadFragment(fragment);
                        return true;

                    case R.id.menu_history:
                        toolbar.setTitle(R.string.menu_history);
                        setSupportActionBar(toolbar);
                        fragment = new HistoryFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.menu_settings:
                        toolbar.setTitle(R.string.menu_settings);
                        setSupportActionBar(toolbar);
                        fragment = new SettingsFragment();
                        loadFragment(fragment);
                        return true;
                }
                return false;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_main, fragment);
        transaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        bottomNavigationView.setSelectedItemId(R.id.menu_home);

//        if(Objects.equals(getIntent().getStringExtra(FRAGMENT_TO_LOAD), "Quiz")){
//            navigation.setSelectedItemId(R.id.fr_addrecord);
//        }else{
//            navigation.setSelectedItemId(R.id.cons_fr_history);
//
//        }
}


}
