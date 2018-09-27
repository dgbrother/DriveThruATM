package com.example.paul5.DTATM_app;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class UserInfoEditActivity extends AppCompatActivity {
    private final String SERVER_URL = "http://35.200.117.1:8080/control_json.jsp";
    EditText eUserName;
    UserInfo userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_edit);

        final String userId = "ID1234";
        userInfo = getUserInfo(userId);

        eUserName = findViewById(R.id.userInfo_userName_editText);
    }

    private UserInfo getUserInfo(String userId) {
        ContentValues params = new ContentValues();
        params.put("action", "userinfo");
        params.put("userid", userId);

        try {
            NetworkTask networkTask = new NetworkTask(SERVER_URL, params);
            JSONObject jsonObject = networkTask.execute().get();
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
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class NetworkTask extends AsyncTask<Void, Void, JSONObject> {
        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {
            this.url = url;
            this.values = values;
        }

        @Override
        protected JSONObject doInBackground(Void... voids) {
            JSONObject result;
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values);
            return result;
        }

        @Override
        protected void onPostExecute(JSONObject s) {
            super.onPostExecute(s);
            eUserName.setText(userInfo.getName());
        }
    }
}