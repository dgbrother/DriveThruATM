package com.example.paul5.DTATM_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ReservationMainActivity extends AppCompatActivity implements View.OnClickListener{
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_main);

        findViewById(R.id.addworkbutton).setOnClickListener(this);

        adapter = new ListViewAdapter();
        ListView listview = findViewById(R.id.reservation_list);
        listview.setAdapter(adapter);
        try {
            String param = URLEncoder.encode("한글","UTF-8");
            String url = "http://35.200.117.1:8080/test.jsp?param="+param;
            NetworkTask networkTask = new NetworkTask(url, null);
            networkTask.execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addworkbutton:
                Intent intent = new Intent(ReservationMainActivity.this, ReservationAddActivity.class);
                startActivity(intent);
                break;
        }
    }
}
