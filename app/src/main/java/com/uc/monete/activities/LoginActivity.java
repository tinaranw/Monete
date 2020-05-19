package com.uc.monete.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.uc.monete.R;
import com.uc.monete.api.BackgroundWorker;
import com.uc.monete.model.History;
import com.uc.monete.model.Users;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {

    EditText email_edit, password_edit;
    Button login;
    TextView loginStatus;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email_edit = findViewById(R.id.editTxtEmail);
        password_edit = findViewById(R.id.editTxtPassword);
        loginStatus = findViewById(R.id.loginstatus);

        login = findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = email_edit.getText().toString().trim();
                password = password_edit.getText().toString().trim();
                loginChecking(email, password);


            }
        });


    }

    private void loginChecking(String email2, String password2) {
        final ArrayList<Users> users = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("email", email2);
        params.put("password", password2);
        String url = "http://iamtinara.com/api/login.php";
        Toast.makeText(LoginActivity.this,email2, Toast.LENGTH_SHORT).show();
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    String login_status = responseObject.getString("login_status");
                    if (login_status.equalsIgnoreCase("logged_in")) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {

                        Log.d("loginstat", login_status);
                        loginStatus.setText("Wrong email or password! Try again!!!");
//                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(intent);
                    }
                } catch (Exception e) {
                    Log.d("ExceptionHistory", "onSuccess: " + e.getMessage());

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailureHistory", "onFailure: " + error.getMessage());
            }
        });

    }
}
