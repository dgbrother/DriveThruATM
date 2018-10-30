package com.example.paul5.DTATM_app.activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.paul5.DTATM_app.ListViewAdapter;
import com.example.paul5.DTATM_app.R;
import com.example.paul5.DTATM_app.RequestHttpURLConnection;
import com.example.paul5.DTATM_app.ReservationWork;

import org.json.JSONObject;

import java.util.ArrayList;

public class ReservationMain extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListViewAdapter adapter;
    private String url = "http://35.200.117.1:8080/control.jsp";
    private ContentValues params;
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_main);

        findViewById(R.id.addworkbutton).setOnClickListener(this);
        findViewById(R.id.backBtn)      .setOnClickListener(this);
        findViewById(R.id.logoutBtn)    .setOnClickListener(this);

        SharedPreferences appData = getSharedPreferences("appData", MODE_PRIVATE);
        currentUserId = appData.getString("id","ID1234");

        TextView nameWelcome = findViewById(R.id.welcome_hostname);
        nameWelcome.setText(appData.getString("name", "방문자")+"님 환영합니다.");

        params = new ContentValues();
        params.put("type",      "reservation");
        params.put("action",    "select");
        params.put("from",      "mobile");
        params.put("userId",    currentUserId);

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ReservationMain.this);
        builder.setTitle("예약 번호 : "+adapter.getNo(position));
        builder.setMessage("정말 삭제하시겠습니까?");

        builder.setPositiveButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                deleteReservation(adapter.getNo(position));
            }
        });

        builder.show();
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
            adapter = new ListViewAdapter();
            for (int i = 0; i < works.size(); i++)
                adapter.addItem(works.get(i));

            return result;
        }

        @Override
        protected void onPostExecute(JSONObject s) {
            super.onPostExecute(s);
            ListView listview = findViewById(R.id.reservation_list);
            listview.setAdapter(adapter);
            listview.setOnItemClickListener(ReservationMain.this);
        }
    }

    private void deleteReservation(String no) {
        ContentValues params = new ContentValues();
        params.put("type",      "reservation");
        params.put("action",    "update");
        params.put("from",      "mobile");
        params.put("userId",    currentUserId);
        params.put("no",        no);

        NetworkTask deleteReservationInfoTask = new NetworkTask(url, params);
        deleteReservationInfoTask.execute();
    }
}