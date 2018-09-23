package com.example.paul5.DTATM_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {
    String id = "abc";
    String pw = "ghqkrth13@";
    EditText idText;
    EditText pwText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button confirmBtn = findViewById(R.id.confirm);
        idText = findViewById(R.id.id);
        pwText = findViewById(R.id.password);
        confirmBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.confirm :
                if (Id_confirm()) {
                    Intent intent = new Intent(Login.this, Main.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private Boolean Id_confirm () {
        if (id.equals(idText.getText().toString()) && pw.equals(pwText.getText().toString()))
            return true;
        else
            return false;
    }

}
