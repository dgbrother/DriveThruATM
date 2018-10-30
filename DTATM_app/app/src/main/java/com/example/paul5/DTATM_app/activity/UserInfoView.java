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

import com.example.paul5.DTATM_app.R;
import com.example.paul5.DTATM_app.RequestHttpURLConnection;
import com.example.paul5.DTATM_app.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

public class UserInfoView extends AppCompatActivity implements View.OnClickListener {
    private final String SERVER_URL = "http://35.200.117.1:8080/control.jsp";
    SharedPreferences appData;
    EditText eUserName, eUserID, eUserPassword, eUserEmail;
    EditText eUserAccount, eUserCarNumber, eUserNFCID;
    Button editButton, okButton;
    String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_edit);

        appData = getSharedPreferences("appData", MODE_PRIVATE);

        eUserName       = findViewById(R.id.userInfo_userName_editText);
        eUserID         = findViewById(R.id.userInfo_userId_editText);
        eUserPassword   = findViewById(R.id.userInfo_password_editText);
        eUserEmail      = findViewById(R.id.userInfo_email_editText);
        eUserAccount    = findViewById(R.id.userInfo_account_editText);
        eUserCarNumber  = findViewById(R.id.userInfo_carNumber_editText);
        eUserNFCID      = findViewById(R.id.userInfo_nfcId_editText);

        editButton      = findViewById(R.id.userInfo_edit_button);
        okButton        = findViewById(R.id.userInfo_ok_button);
        editButton      .setOnClickListener(this);
        okButton        .setOnClickListener(this);

        currentUserId = appData.getString("id","ID1234");

        UserInfo userInfo = new UserInfo(
                appData.getString("name", "error"),
                appData.getString("id", ""),
                appData.getString("password", ""),
                appData.getString("email", ""),
                appData.getString("account", ""),
                appData.getString("carNumber", ""),
                appData.getString("nfcId", "")
        );
        setUserInfo(userInfo);
        setEnables(false);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.userInfo_edit_button:
                editUserInfo();
                break;
            case R.id.userInfo_ok_button:
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent);
                break;
        }
    }

    private class NetworkTask extends AsyncTask<Void, Void, UserInfo> {
        private String url;
        private ContentValues values;
        ProgressDialog spinnerDialog = new ProgressDialog(UserInfoView.this);

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
        protected UserInfo doInBackground(Void... voids) {
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            JSONObject jsonResult = requestHttpURLConnection.request(url, values);

            UserInfo userInfo = UserInfo.jsonToUserInfo(jsonResult);
            saveCurrentUser(jsonResult);
            return userInfo;
        }

        @Override
        protected void onPostExecute(UserInfo userInfo) {
            super.onPostExecute(userInfo);
            spinnerDialog.dismiss();

            setUserInfo(userInfo);
            setEnables(false);
        }
    }

    // 유저 정보 수정
    private void editUserInfo() {
        String status = editButton.getText().toString();

        /**
         * '수정' 버튼을 누르면 edit Text가 수정 가능하게 바뀌고
         * 버튼 text 가 '저장'으로 바뀜
         * 수정 중일 때에는 완료 버튼 비활성화
         */
        if(status.equals("수정")) {
            setEnables(true);

            editButton.setText("저장");
            okButton.setEnabled(false);
        }
        if(status.equals("저장")) {
            updateUserInfo();
            setEnables(false);

            editButton.setText("수정");
            okButton.setEnabled(true);
        }
    }

    /// edit Text를 유저 정보로 채움
    private void setUserInfo(UserInfo userInfo) {
        eUserName       .setText(userInfo.getName());
        eUserID         .setText(userInfo.getId());
        eUserPassword   .setText(userInfo.getPassword());
        eUserEmail      .setText(userInfo.getEmail());
        eUserAccount    .setText(userInfo.getAccount());
        eUserCarNumber  .setText(userInfo.getCarNumber());
        eUserNFCID      .setText(userInfo.getNfcId());
    }

    private void updateUserInfo() {
        ContentValues params = new ContentValues();
        params.put("type",      "user");
        params.put("action",    "update");
        params.put("userId",    currentUserId);
        params.put("id",        eUserID         .getText().toString());
        params.put("password",  eUserPassword   .getText().toString());
        params.put("name",      eUserName       .getText().toString());
        params.put("email",     eUserEmail      .getText().toString());
        params.put("account",   eUserAccount    .getText().toString());
        params.put("carNumber", eUserCarNumber  .getText().toString());
        params.put("nfcId",     eUserNFCID      .getText().toString());

        NetworkTask getUserInfoTask = new NetworkTask(SERVER_URL, params);
        getUserInfoTask.execute();
    }

    // edit Text의 활성화 토글
    private void setEnables(boolean enables) {
        eUserName       .setEnabled(enables);
        eUserID         .setEnabled(enables);
        eUserPassword   .setEnabled(enables);
        eUserEmail      .setEnabled(enables);
        eUserAccount    .setEnabled(enables);
        eUserCarNumber  .setEnabled(enables);
        eUserNFCID      .setEnabled(enables);
    }

    private void saveCurrentUser(JSONObject jsonObject) {
        /**
         * SharedPreferences 정보의 수정은 반드시 Editor를 사용해야 함.
         * 수정 후 apply() 해야 수정된 정보가 저장됨.
         */
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