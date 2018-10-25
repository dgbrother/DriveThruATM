package com.example.paul5.DTATM_app.activity;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.paul5.DTATM_app.R;
import com.example.paul5.DTATM_app.RequestHttpURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences appData;
    EditText idText;
    EditText pwText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        appData = getSharedPreferences("appData", MODE_PRIVATE);
        String savedIdForAutoLogin = appData.getString("id","none");
        if(!savedIdForAutoLogin.equals("none")) {
            loginConfirm(true);
        }

        Button confirmBtn = findViewById(R.id.confirm);
        idText = findViewById(R.id.id);
        pwText = findViewById(R.id.password);
        confirmBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.confirm :
                Id_confirm();
                break;
        }
    }

    private class NetworkTask extends AsyncTask<Void, Void, Boolean> {
        private String url;
        private ContentValues values;
        ProgressDialog spinnerDialog = new ProgressDialog(Login.this);

        public NetworkTask(String url, ContentValues values) {
            this.url = url;
            this.values = values;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            spinnerDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            spinnerDialog.setMessage("정보를 불러오는 중입니다.");
            spinnerDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            JSONObject jsonResult = requestHttpURLConnection.request(url, values);

            String isConfirm = "";
            try {
                isConfirm = jsonResult.getString("isConfirm");
                if(isConfirm.equals("true"))
                    saveCurrentUser(jsonResult);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return isConfirm.equals("true");
        }

        @Override
        protected void onPostExecute(Boolean isConfirm) {
            super.onPostExecute(isConfirm);
            spinnerDialog.dismiss();

            loginConfirm(isConfirm);
        }
    }

    private void Id_confirm () {
        String SERVER_URL = "http://35.200.117.1:8080/control.jsp";
        ContentValues params = new ContentValues();
        params.put("type",  "user");
        params.put("action","login");
        params.put("inputId",       idText.getText().toString());
        params.put("inputPassword", pwText.getText().toString());

        NetworkTask loginCheckTask = new NetworkTask(SERVER_URL, params);
        loginCheckTask.execute();
    }

    private void loginConfirm(boolean isConfirm) {
        if (isConfirm) {
            Intent intent = new Intent(Login.this, Main.class);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveCurrentUser(JSONObject jsonObject) {
        try {
            SharedPreferences.Editor editor = appData.edit();
            editor.putString("id",          jsonObject.getString("id"));
            editor.putString("name",        jsonObject.getString("name"));
            editor.putString("password",    jsonObject.getString("password"));
            editor.putString("email",       jsonObject.getString("email"));
            editor.putString("account",     jsonObject.getString("account"));
            editor.putString("carNumber",   jsonObject.getString("carnumber"));
            editor.putString("nfcId",       jsonObject.getString("nfc"));
            editor.apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
