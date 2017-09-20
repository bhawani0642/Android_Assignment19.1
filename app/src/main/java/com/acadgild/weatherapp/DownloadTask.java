package com.acadgild.weatherapp;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Pri on 8/30/2017.
 */

public class DownloadTask extends AsyncTask<String,Void,String> {
    String result="";
    URL url;
    HttpURLConnection httpURLConnection=null;
    @Override
    protected String doInBackground(String... urls) {
       ;

        try {
            url= new URL(urls[0]);
            httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream in= httpURLConnection.getInputStream();
            InputStreamReader reader= new InputStreamReader(in);
            int data= reader.read();
            while (data!=-1){
                char current= (char)data;
                result+=current;
                data=reader.read();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        try {
            JSONObject jsonObject= new JSONObject(result);
            JSONObject main=new JSONObject(jsonObject.getString("main"));
            String temperature = main.getString("temp");
            String placeName = jsonObject.getString("name");

            MainActivity.place.setText(placeName);
            MainActivity.temp.setText(temperature);
            MainActivity.temp.setText(temperature);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
