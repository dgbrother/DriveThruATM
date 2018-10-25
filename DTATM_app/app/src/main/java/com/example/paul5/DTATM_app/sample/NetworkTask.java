package com.example.paul5.DTATM_app.sample;

import android.content.ContentValues;
import android.os.AsyncTask;

import com.example.paul5.DTATM_app.RequestHttpURLConnection;

import org.json.JSONObject;

import java.util.ArrayList;

public class NetworkTask extends AsyncTask<Void, Void, JSONObject> {
    private String url;
    private ContentValues values;

    public NetworkTask(String url, ContentValues values) {
        this.url = url;
        this.values = values;
    }

    @Override
    protected JSONObject doInBackground(Void... voids) {
        RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
        JSONObject result = requestHttpURLConnection.request(url, values);

        return result;
    }

    @Override
    protected void onPostExecute(JSONObject s) {
        super.onPostExecute(s);

    }
}
