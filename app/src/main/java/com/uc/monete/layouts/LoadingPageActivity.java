package com.uc.monete.layouts;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.airbnb.lottie.LottieAnimationView;
import com.uc.monete.R;
import com.uc.monete.activities.LoginActivity;
import com.uc.monete.activities.MainActivity;
import com.uc.monete.fragment.HistoryFragment;
import com.uc.monete.fragment.HomeFragment;

import java.util.Timer;
import java.util.TimerTask;

public class LoadingPageActivity extends AppCompatActivity {

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_page);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {



            }
        }, 3000);


    }
}
