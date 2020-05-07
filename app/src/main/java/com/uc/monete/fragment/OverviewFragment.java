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

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.uc.monete.R;
import com.uc.monete.activities.ApiClient;
import com.uc.monete.activities.ApiInterface;
import com.uc.monete.activities.Growth;
import com.uc.monete.model.History;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Response;


public class OverviewFragment extends Fragment {
    Toolbar toolbar;
    TextView incomeText, expenseText, highexText, lowexText, totalbalanceText;
    private BarChart mBarChart;
    private PieChart mPieChart;

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
//        return inflater.inflate(R.layout.fragment_overview, container, false);

        View view = inflater.inflate(R.layout.fragment_overview,container,false);
        mBarChart = view.findViewById(R.id.barChart);
        mPieChart = view.findViewById(R.id.pieChart);
        getGrowthChart(getArguments().getString("method"));
        return view;
    }

    private void getGrowthChart(String method){

        Call<List<Growth>> call = ApiClient.getApiClient().create(ApiInterface.class).getGrowthInfo();

        call.enqueue(new Callback<List<Growth>>(){

            @Override
            public void onResponsse(Call<List<Growth>> call, Response<List<Growth>> response){
                if (response.body() != null){
                    if(method.equals("bars")){
                        List<BarEntry>barEntries = new ArrayList<>();
                        for(Growth growth : response.body()){
                            BarEntries.add(new BarEntry(growth.getYear(),growth.getGrowth_Rate()));
                        }
                        BarDataSet barDataSet = new BarDataSet(barEntries,"Growth");
                        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                        BarData barData = new BarData(barDataSet);
                        barData.setBarWidth(0.9f);

                        mBarChart.setVisibility(View.VISIBLE);
                        mBarChart.animateY(5000);
                        mBarChart.setData(barData);
                        mBarChart.setFitBars(true);

                        Description description = new Description();
                        description.setText("Growth Rate Per Year");
                        mBarChart.setDescription(description);
                        mBarChart.invalidate();
                    }
                    else if(method.equals("pie")){

                        List<PieEntry> pieEntries = new ArrayList<>();
                        for (Growth growth : response.body()){
                            pieEntries.add(new PieEntry(growth.getGrowth()_Rate(),Integer.toString(growth.getYear())));
                        }

                        mPieChart.setVisibility(View.VISIBLE);
                        mPieChart.animateXY(5000,5000);

                        PieDataSet pieDataSet = new PieDataSet(pieEntries,"Growth per year");
                        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                        PieData pieData = new PieData(pieDataSet);

                        mPieChart.setData(pieData);
                         Description description = new
                }
            }
            @Override
            public void onFailure(Call<List<Growth>> call, Throwable t){

            }
        });
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
