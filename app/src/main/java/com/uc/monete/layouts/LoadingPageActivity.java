package com.uc.monete.layouts;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.uc.monete.R;
import com.uc.monete.activities.LoginActivity;
import com.uc.monete.activities.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class LoadingPageActivity extends AppCompatActivity {

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landingpage);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadingPageActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);


    }
}
