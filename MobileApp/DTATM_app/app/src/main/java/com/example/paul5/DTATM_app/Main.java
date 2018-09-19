package com.example.paul5.DTATM_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button reserveBtn = findViewById(R.id.reserve);
        Button searchBtn = findViewById(R.id.search);
        Button usereditBtn = findViewById(R.id.user_edit);
        Button logoutBtn = findViewById(R.id.logout);
        reserveBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
        usereditBtn.setOnClickListener(this);
        logoutBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.reserve :
                break;
            case R.id.search :
                break;
            case R.id.user_edit :
                Intent intent3 = new Intent(Main.this, UserEdit.class);
                startActivity(intent3);
                break;
            case R.id.logout :
                Intent intent4 = new Intent(Main.this, Login.class);
                startActivity(intent4);
                Toast.makeText(getApplicationContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
