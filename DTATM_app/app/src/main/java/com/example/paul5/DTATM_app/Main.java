package com.example.paul5.DTATM_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences appData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        appData = getSharedPreferences("appData", MODE_PRIVATE);
        Log.d("hello", appData.getAll().toString());

        Button reserveBtn = findViewById(R.id.reserveBtn);
        Button searchBtn = findViewById(R.id.searchBtn);
        Button usereditBtn = findViewById(R.id.user_editBtn);
        Button logoutBtn = findViewById(R.id.logoutBtn);
        reserveBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
        usereditBtn.setOnClickListener(this);
        logoutBtn.setOnClickListener(this);

        Toast.makeText(getApplicationContext(), "login: "+appData.getString("id","none"), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.reserveBtn :
                Intent intent = new Intent(Main.this, ReservationMainActivity.class);
                startActivity(intent);
                break;
            case R.id.searchBtn :
//                Intent intent2 = new Intent(Main.this, ReserveList.class);
//                startActivity(intent2);
                break;
            case R.id.user_editBtn :
                Intent intent3 = new Intent(Main.this, UserInfoEditActivity.class);
                startActivity(intent3);
                break;
            case R.id.logoutBtn :
                deleteCurrentUserId();

                Intent intent4 = new Intent(Main.this, Login.class);
                startActivity(intent4);
                Toast.makeText(getApplicationContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

    private void deleteCurrentUserId() {
        SharedPreferences.Editor editor = appData.edit();
        editor.remove("id");
        editor.remove("password");
        editor.remove("email");
        editor.remove("account");
        editor.remove("carNumber");
        editor.remove("nfcId");
        editor.apply();
    }
}
