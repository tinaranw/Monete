package com.uc.monete.fragment;

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

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.uc.monete.R;
import com.uc.monete.model.History;
import com.uc.monete.model.Records;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class OverviewFragment extends Fragment {
    Toolbar toolbar;
    TextView incomeText, expenseText, highexText, lowexText, totalbalanceText;
    ArrayList<Records> records = new ArrayList<>();

    private static final String TAG = "fragment_overview";
    private PieChart mChart;

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
        mChart = view.findViewById(R.id.piechart);
        getChart();


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

    private void getChart(){
        mChart.setUsePercentValues(true);

        Description desc = new Description();
        desc.setText("This is your total balance pie chart");
        desc.setTextSize(20f);


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
                    int income_value = Integer.parseInt(income);
                    int expense_value = Integer.parseInt(expense);
                    List<PieEntry> value = new ArrayList<>();
                    value.add(new PieEntry(income_value, "Income"));
                    value.add(new PieEntry(expense_value, "Expense"));

                    PieDataSet pieDataSet = new PieDataSet(value, "Balance");
                    PieData pieData = new PieData(pieDataSet);
                    mChart.setData(pieData);

                    pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                } catch (Exception e) {
                    Log.d("ExceptionHistory", "onSuccess: " + e.getMessage());

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailureHistory", "onFailure: " + error.getMessage());
            }
        });

//        final ArrayList<History> histories = new ArrayList<>();
//
//        final
//        AsyncHttpClient client = new AsyncHttpClient();
//        String url = "http://iamtinara.com/api/list.php";
//
//        client.get(url, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                try {
//                    String result = new String(responseBody);
//                    JSONObject responseObject = new JSONObject(result);
//                    JSONArray list = responseObject.getJSONArray("history_monete");
//
////
//                    for (int i = 0; i < list.length(); i++) {
//                        JSONObject obj = list.getJSONObject(i);
//                        Records value = new Records(obj.getString("id"), obj.getString("amount"),
//                                obj.getString("type"));
//                        records.add(value);
//                    }
//
//                    for (int i = 0; i < records.size(); i++) {
//                        String id = Records.getValue_id();
//                        String type = Records.getValue_type();
//                        String value = Records.getValue_amount();
//
//                        if(type.equalsIgnoreCase("Expense")){
//                            yValues.add(new Entry(Integer.parseInt(id),Integer.parseInt(value)));
//                        }
//
//                    }
//                    LineDataSet set1 = new LineDataSet(yValues,"Data Set 1");
//
//                    set1.setFillAlpha(110);
//
//                    ArrayList<ILineDataSet> dataSets = new ArrayList<>();
//                    dataSets.add(set1);
//
//                    LineData data = new LineData(dataSets);
//
//                    mChart.setData(data);
//
//                } catch (Exception e) {
//                    Log.d("ExceptionHistory", "onSuccess: " + e.getMessage());
//
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                Log.d("onFailureHistory", "onFailure: " + error.getMessage());
//            }
//        });

//        Records.add(new Entry(0,60f));
//        Records.add(new Entry(1,50f));
//        Records.add(new Entry(2,70f));
//        Records.add(new Entry(3,30f));
//        Records.add(new Entry(4,50f));
//        Records.add(new Entry(5,60f));
//        Records.add(new Entry(6,75f));




    }
}
