package com.uc.monete.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uc.monete.R;

public class AddRecordActivity extends AppCompatActivity {

    FloatingActionButton home;
    FloatingActionButton settings;
    FloatingActionButton history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home = findViewById(R.id.home);
        settings = findViewById(R.id.settings);
        history = findViewById(R.id.history);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (AddRecordActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (AddRecordActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (AddRecordActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });
    }
}
