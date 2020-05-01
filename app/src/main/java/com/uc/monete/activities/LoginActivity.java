package com.uc.monete.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.uc.monete.R;
import com.uc.monete.api.BackgroundWorker;

public class LoginActivity extends AppCompatActivity {

    EditText email_edit, password_edit;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email_edit = findViewById(R.id.editTxtEmail);
        password_edit = findViewById(R.id.editTxtPassword);

        login = findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnLogin(v);

                Intent intent = new Intent (LoginActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });


    }

    public void OnLogin(View view) {
        String email, password;

        email = email_edit.getText().toString();
        password = password_edit.getText().toString();
        String activity_type = "login";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(activity_type, email, password);
    }
}
