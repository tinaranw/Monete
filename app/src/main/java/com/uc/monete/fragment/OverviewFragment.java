package com.uc.monete.fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.uc.monete.R;
import com.uc.monete.model.History;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class OverviewFragment extends Fragment {
    Toolbar toolbar;
    TextView incomeText, expenseText, highexText, lowexText, totalbalanceText;


    public OverviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        toolbar = getActivity().findViewById(R.id.toolbar_main);
        toolbar.setTitle("Overview");
        return inflater.inflate(R.layout.fragment_overview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        incomeText = view.findViewById(R.id.incomebal);
        expenseText = view.findViewById(R.id.expensebal);
        highexText = view.findViewById(R.id.highexpensebal);
        lowexText = view.findViewById(R.id.lowexpensebal);
        totalbalanceText = view.findViewById(R.id.totalexpensebal);

        getOverview();
        getMaxExpense();
        getMinExpense();
    }

    private void getOverview(){
        final ArrayList<History> histories = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://iamtinara.com/api/overview.php";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    String curbalancetext = responseObject.getString("cur_balance");
                    String income = responseObject.getString("income");
                    String expense = responseObject.getString("expense");
                    incomeText.setText(income);
                    expenseText.setText(expense);
                    totalbalanceText.setText(curbalancetext);
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
    private void getMaxExpense(){
        final ArrayList<History> histories = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://iamtinara.com/api/maxexpense.php";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    String max = responseObject.getString("max");
                    highexText.setText(max);
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
    private void getMinExpense(){
        final ArrayList<History> histories = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://iamtinara.com/api/minexpense.php";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    String min = responseObject.getString("min");
                    lowexText.setText(min);
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
