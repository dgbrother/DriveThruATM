/*
package com.example.jwho_.atmapp;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class ReservationMainActivity extends AppCompatActivity implements View.OnClickListener{
    private ListViewAdapter adapter;
    private final String SERVER_URL = "http://35.200.117.1:8080/test.jsp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_main);

        findViewById(R.id.addworkbutton).setOnClickListener(this);

        adapter = new ListViewAdapter();
        adapter.addItem("name");
        ListView listview = findViewById(R.id.reservation_list);
        listview.setAdapter(adapter);

        ContentValues params = new ContentValues();
        params.put("param", "한글");      // ContentValues가 아닌 문자열 그대로 한글을 넣을경우 encoding 에러
        params.put("action", "test");

        NetworkTask networkTask = new NetworkTask(SERVER_URL, params);
        networkTask.execute();
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
*/