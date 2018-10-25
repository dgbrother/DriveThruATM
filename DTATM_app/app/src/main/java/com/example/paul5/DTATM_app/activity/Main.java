package com.example.paul5.DTATM_app.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paul5.DTATM_app.R;

public class Main extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences appData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        appData = getSharedPreferences("appData", MODE_PRIVATE);
        TextView currentUserName = findViewById(R.id.main_user_name);
        currentUserName.setText(appData.getString("name", "none"));

        findViewById(R.id.reserveBtn).setOnClickListener(this);
        findViewById(R.id.user_editBtn).setOnClickListener(this);
        findViewById(R.id.logoutBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.reserveBtn :
                Intent intent = new Intent(Main.this, ReservationMain.class);
                startActivity(intent);
                break;
            case R.id.user_editBtn :
                Intent intent3 = new Intent(Main.this, UserInfoView.class);
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
