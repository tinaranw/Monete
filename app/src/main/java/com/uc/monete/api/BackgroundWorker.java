package com.uc.monete.api;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class BackgroundWorker extends AsyncTask<String, Void, String> {


    AlertDialog alertDialog;


    Context context;

    public BackgroundWorker(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {

        String user_id = params[0];
        String insertRecord_url = "http://iamtinara.com/api/insert.php";
        if(user_id.equals("1")){

            try {
                String amount = params[1];
                String type = params[2];
                String tag = params[3];
                String date = params[4];
                String memo = params[5];
                String cur_balance = params[6];
                URL url = new URL(insertRecord_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("amount", "UTF-8")+"="+URLEncoder.encode(amount, "UTF-8")+"&"
                        +URLEncoder.encode("type", "UTF-8")+"="+URLEncoder.encode(type, "UTF-8")+"&"
                        +URLEncoder.encode("tag", "UTF-8")+"="+URLEncoder.encode(tag, "UTF-8")+"&"
                        +URLEncoder.encode("date", "UTF-8")+"="+URLEncoder.encode(date, "UTF-8")+"&"
                        +URLEncoder.encode("memo", "UTF-8")+"="+URLEncoder.encode(memo, "UTF-8")+"&"
                        +URLEncoder.encode("cur_balance", "UTF-8")+"="+URLEncoder.encode(cur_balance, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!=null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("New Data Added");
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

