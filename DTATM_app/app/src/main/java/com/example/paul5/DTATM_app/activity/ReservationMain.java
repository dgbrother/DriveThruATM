package com.example.paul5.DTATM_app.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.paul5.DTATM_app.ListViewAdapter;
import com.example.paul5.DTATM_app.R;
import com.example.paul5.DTATM_app.RequestHttpURLConnection;
import com.example.paul5.DTATM_app.ReservationWork;

import org.json.JSONObject;

import java.util.ArrayList;

public class ReservationMain extends AppCompatActivity implements View.OnClickListener{
    private ListViewAdapter adapter;
    private String url = "http://35.200.117.1:8080/control.jsp";
    private ContentValues params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_main);

        findViewById(R.id.addworkbutton).setOnClickListener(this);
        findViewById(R.id.backBtn)      .setOnClickListener(this);
        findViewById(R.id.logoutBtn)    .setOnClickListener(this);

        SharedPreferences appData = getSharedPreferences("appData", MODE_PRIVATE);
        adapter = new ListViewAdapter();

        params = new ContentValues();
        params.put("type",      "reservation");
        params.put("action",    "select");
        params.put("from",      "mobile");
        params.put("userId",    appData.getString("id","ID1234"));

        NetworkTask getReservationInfoTask = new NetworkTask(url, params);
        getReservationInfoTask.execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addworkbutton:
                Intent intent = new Intent(ReservationMain.this, ReservationAdd.class);
                startActivity(intent);
                break;
            case R.id.backBtn:
                Intent intent2 = new Intent(ReservationMain.this, Main.class);
                startActivity(intent2);
                break;
            case R.id.logoutBtn:
                Intent intent3 = new Intent(ReservationMain.this, Login.class);
                startActivity(intent3);
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

            ArrayList<ReservationWork> works = ReservationWork.jsonToReserveInfo(result);
            for (int i = 0; i < works.size(); i++)
                adapter.addItem(works.get(i));

            return result;
        }

        @Override
        protected void onPostExecute(JSONObject s) {
            super.onPostExecute(s);

            ListView listview = findViewById(R.id.reservation_list);
            listview.setAdapter(adapter);
        }
    }
}