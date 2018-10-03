package com.example.paul5.DTATM_app;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences appData;
    EditText idText;
    EditText pwText;
    String inputUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        /**
         * 앱 내부에 "appData"라는 파일을 만들고 DB 처럼 사용
         * 이미 appData 파일이 있을 경우 있는 파일에 대한 정보를 가져옴
         */
        appData = getSharedPreferences("appData", MODE_PRIVATE);

        /**
         * appData에서 정보를 가져올 때는 ex) 현재 로그인 한 유저 ID
         * String currnetId =
         *      appData.getString("currentUserId", "저장된 값이 없을 경우 반환되는 값");
         */

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

    private void Id_confirm () {
        /**
         * SERVER_URL : Login 관련 기능을 수행하는 jsp 주소
         * action : login (지금은 login 밖에 없음. 추후 필요한 기능 추가예정)
         * inputId와 inputPassword Parameter로 입력받은 ID, Password 전달
         * 로그인 가능 ID/PW
         * ID : ID1234
         * PW : PW1234!!
         */
        String SERVER_URL = "http://35.200.117.1:8080/login.jsp";
        ContentValues params = new ContentValues();
        params.put("action", "login");
        params.put("inputId", idText.getText().toString());
        params.put("inputPassword", pwText.getText().toString());
        inputUserId = idText.getText().toString();

        NetworkTask loginCheckTask = new NetworkTask(SERVER_URL, params);
        loginCheckTask.execute();
    }

    /**
     * 로그인 정보 확인을 요청하는 NetworkTask
     * 로그인 정보가 일치하면 다음 Activity로 넘어가고
     * 일치하지 않으면 안내 Toast를 출력한다.
     *
     * SpinnerDialog는 로딩 시 로딩중 안내 메시지박스 표시. 너무 짧아서 거의 안보임
     */
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

            /**
             * 넘어오는 JSON 형식
             * | JSONObject ========================|
             * |    isConfirm: "true" or "false"    |
             * =====================================|
             */
            String isConfirm = "";
            try {
                isConfirm = jsonResult.getString("isConfirm");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // isConfirm 값이 "true"이면 true 반환
            return isConfirm.equals("true");
        }

        @Override
        protected void onPostExecute(Boolean isConfirm) {
            super.onPostExecute(isConfirm);
            spinnerDialog.dismiss();

            if (isConfirm) {
                saveCurrentUserID();

                Intent intent = new Intent(Login.this, Main.class);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveCurrentUserID() {
        /**
         * SharedPreferences 정보의 수정은 반드시 Editor를 사용해야 함.
         * 수정 후 apply() 해야 수정된 정보가 저장됨.
         */
        SharedPreferences.Editor editor = appData.edit();
        editor.putString("currentUserId", inputUserId);
        editor.apply();
    }
}
