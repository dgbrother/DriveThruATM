package com.example.paul5.DTATM_app;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReserveList extends AppCompatActivity implements View.OnClickListener {
    private ListViewAdapter adapter;
    SharedPreferences appData;
    private String url = "http://35.200.117.1:8080/control.jsp";
    private ContentValues params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_list);

        appData = getSharedPreferences("appData", MODE_PRIVATE);
        adapter = new ListViewAdapter();

        Button backBtn = findViewById(R.id.backBtn);
        Button logoutBtn = findViewById(R.id.logoutBtn);
        backBtn.setOnClickListener(this);
        logoutBtn.setOnClickListener(this);


        params = new ContentValues();
        params.put("type", "reservation");
        params.put("action", "select");
        // 모바일 일 경우
        params.put("from", "mobile");
        params.put("userId", "ID1234");

        NetworkTask getReservationInfoTask = new NetworkTask(url, params);
        getReservationInfoTask.execute();


        ListView listview = findViewById(R.id.reservation_list); // reservation_list 는 리스트뷰 이름
        listview.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBtn:
                Intent intent1 = new Intent(ReserveList.this, Main.class);
                startActivity(intent1);
                break;
            case R.id.logoutBtn:
                Intent intent2 = new Intent(ReserveList.this, Login.class);
                startActivity(intent2);
                break;
        }
    }

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
            Log.d("hello", "json result : "+ result);
            ArrayList<ReservationWork> works = ReservationWork.jsonToReserveInfo(result);

            for (int i = 0; i < works.size(); i++) {
                adapter.addItem(works.get(i));
            }

            return result;
        }

        @Override
        protected void onPostExecute(JSONObject s) {
            super.onPostExecute(s);

        }
    }
}
