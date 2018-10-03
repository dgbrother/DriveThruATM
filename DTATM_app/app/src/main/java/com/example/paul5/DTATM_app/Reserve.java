package com.example.paul5.DTATM_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Reserve extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve);

        Button backBtn = findViewById(R.id.backBtn);
        Button logoutBtn =findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.backBtn :
                Intent intent = new Intent(Reserve.this, Main.class);
                startActivity(intent);
                break;
            case R.id.logoutBtn :
                Intent intent2 = new Intent(Reserve.this, Login.class);
                startActivity(intent2);
                Toast.makeText(getApplicationContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}