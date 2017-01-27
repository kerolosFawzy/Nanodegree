package com.massive.readbook;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.massive.readbook.utiles.IcallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class WebCall extends AsyncTask<String, Object, ArrayList<Item>> {
    private Context mContext;
    IcallBack icallBack;
    ArrayList<Item> books;

    public WebCall(IcallBack back, Context context) {
        this.icallBack = back;
        this.mContext = context;
    }

    public ArrayList<Item> formatMyJson(String mjsonString) throws JSONException {
        JSONObject object = new JSONObject(mjsonString);
        JSONArray jsonArray = object.getJSONArray("items");

        Type listType = new TypeToken<ArrayList<Item>>(){}.getType();
        books = new Gson().fromJson(jsonArray.toString(), listType);
        return books;
    }

    @Override
    protected ArrayList<Item> doInBackground(String... params) {
        HttpURLConnection httpURLConnection = null;
        BufferedReader reader = null;
        String jsonstr = null;
        try {
            Uri builtUri = Uri.parse(params[0]).buildUpon().build();
            URL uRl = new URL(builtUri.toString());
            httpURLConnection = (HttpURLConnection) uRl.openConnection();
            httpURLConnection.connect();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            jsonstr = response.toString();
        } catch (Exception e) {
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            return formatMyJson(jsonstr);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Item> books) {
        icallBack.onPostExcuteCallBack(books);
    }
}

