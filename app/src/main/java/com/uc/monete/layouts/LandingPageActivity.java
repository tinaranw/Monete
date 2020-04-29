package com.uc.monete.layouts;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.uc.monete.R;

public class LandingPageActivity extends AppCompatActivity {

        LottieAnimationView wave;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            wave = findViewById(R.id.wave);
            wave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wave.playAnimation();
                    Toast.makeText(LandingPageActivity.this, "Hi there, I'm Monete!", Toast.LENGTH_SHORT).show();
                    //---- Your code here------
                }
            });


        }
    }

