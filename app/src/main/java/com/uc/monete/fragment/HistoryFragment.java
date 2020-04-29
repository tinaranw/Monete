package com.uc.monete.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.uc.monete.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import com.uc.monete.adapter.CardHistory;
import com.uc.monete.model.History;

import cz.msebera.android.httpclient.Header;


public class HistoryFragment extends Fragment {

    public HistoryFragment() {
        // Required empty public constructor
    }

    private RecyclerView rvHistory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_history, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvHistory = view.findViewById(R.id.rv_history);
        getHistory();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void getHistory() {
        final ArrayList<History> histories = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://iamtinara.com/api/list.php";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("history_monete");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject obj = list.getJSONObject(i);
                        History s = new History(obj.getString("id"), obj.getString("user_id"),
                                obj.getString("amount"), obj.getString("type"),
                                obj.getString("tag"), obj.getString("date"), obj.getString("memo"), obj.getString("cur_balance"));

                        histories.add(s);
                    }
                    Log.d("checksize", "" + histories.size());
                    showHistory(histories);
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
    private void showHistory(ArrayList<History> histories) {
        rvHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        CardHistory cardHistory = new CardHistory(getContext());
        cardHistory.setListHistory(histories);
        rvHistory.setAdapter(cardHistory);
    }

}
