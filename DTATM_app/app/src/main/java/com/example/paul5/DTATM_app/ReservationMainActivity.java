package com.example.paul5.DTATM_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ReservationMainActivity extends AppCompatActivity implements View.OnClickListener{
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_main);

        findViewById(R.id.addworkbutton).setOnClickListener(this);

        Button backBtn = findViewById(R.id.backBtn);
        Button logoutBtn = findViewById(R.id.logoutBtn);
        backBtn.setOnClickListener(this);
        logoutBtn.setOnClickListener(this);

        adapter = new ListViewAdapter();
        adapter.addItem("name");
        ListView listview = findViewById(R.id.reservation_list);
        listview.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addworkbutton:
                Intent intent = new Intent(ReservationMainActivity.this, ReservationAddActivity.class);
                startActivity(intent);
                break;
            case R.id.backBtn:
                Intent intent2 = new Intent(ReservationMainActivity.this, Main.class);
                startActivity(intent2);
                break;
            case R.id.logoutBtn:
                Intent intent3 = new Intent(ReservationMainActivity.this, Login.class);
                startActivity(intent3);
                break;
        }
    }
}