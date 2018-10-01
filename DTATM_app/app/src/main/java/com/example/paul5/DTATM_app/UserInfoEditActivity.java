package com.example.paul5.DTATM_app;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserInfoEditActivity extends AppCompatActivity implements View.OnClickListener {
    private final String SERVER_URL = "http://35.200.117.1:8080/user_info.jsp";
    EditText eUserName, eUserID, eUserPassword, eUserEmail;
    EditText eUserAccount, eUserCarNumber, eUserNFCID;
    Button editButton, okButton;
    String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_edit);

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

        // 원래는 로그인 정보 가져옴
        currentUserId = "ID1234";

        ContentValues params = new ContentValues();
        params.put("action", "select");
        params.put("userid", currentUserId);

        NetworkTask getUserInfoTask = new NetworkTask(SERVER_URL, params);
        getUserInfoTask.execute();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.userInfo_edit_button:
                editUserInfo();
                break;
            case R.id.userInfo_ok_button:
                finish();
                break;
        }
    }

    private class NetworkTask extends AsyncTask<Void, Void, UserInfo> {
        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {
            this.url = url;
            this.values = values;
        }

        @Override
        protected UserInfo doInBackground(Void... voids) {
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            JSONObject jsonResult = requestHttpURLConnection.request(url, values);

            UserInfo userInfo = jsonToUserInfo(jsonResult);
            return userInfo;
        }

        @Override
        protected void onPostExecute(UserInfo userInfo) {
            super.onPostExecute(userInfo);
            setUserInfo(userInfo);
            setEnables(false);
        }
    }

    private UserInfo jsonToUserInfo(JSONObject jsonObject) {
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            JSONObject jobj = jsonArray.getJSONObject(0);

            UserInfo userInfo = new UserInfo(
                    jobj.getString("name"),
                    jobj.getString("id"),
                    jobj.getString("password"),
                    jobj.getString("email"),
                    jobj.getString("account"),
                    jobj.getString("carnumber"),
                    jobj.getString("nfc")
            );
            return userInfo;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
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
        params.put("action", "update");
        params.put("userid", currentUserId);
        params.put("id", eUserID.getText().toString());
        params.put("password", eUserPassword.getText().toString());
        params.put("name", eUserName.getText().toString());
        params.put("email", eUserEmail.getText().toString());
        params.put("account", eUserAccount.getText().toString());
        params.put("carNumber", eUserCarNumber.getText().toString());
        params.put("nfcid", eUserNFCID.getText().toString());

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
}