//package com.example.paul5.DTATM_app;
//
//import android.app.ProgressDialog;
//import android.content.ContentValues;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ListView;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class ReserveList extends AppCompatActivity implements View.OnClickListener {
//    private ListViewAdapter adapter;
//    private final String SERVER_URL = "http://35.200.117.1:8080/test_ReserveInfo.jsp";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.reserve_list);
//
//        Button backBtn = findViewById(R.id.backBtn);
//        Button logoutBtn = findViewById(R.id.logoutBtn);
//
//        backBtn.setOnClickListener(this);
//        logoutBtn.setOnClickListener(this);
//
//        adapter = new ListViewAdapter();
//        adapter.addItem("name");
//        adapter.addItem("item2");
//        ListView listview = findViewById(R.id.reservation_list); // reservation_list 는 리스트뷰 이름
//        listview.setAdapter(adapter);
//
//        //DB 연결부분
//        ContentValues params = new ContentValues();
//        params.put("userid", "1111");
//        params.put("from", "mobile");
//
//        ReserveList.NetworkTask getUserInfoTask = new NetworkTask(SERVER_URL, params);
//        getUserInfoTask.execute();
//    }
//
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.backBtn:
//                Intent intent1 = new Intent(ReserveList.this, Main.class);
//                startActivity(intent1);
//                break;
//            case R.id.logoutBtn:
//                Intent intent2 = new Intent(ReserveList.this, Login.class);
//                startActivity(intent2);
//                break;
//        }
//    }
//
//    private class NetworkTask extends AsyncTask<Void, Void, ReservationWork> {
//        private String url;
//        private ContentValues values;
//        ProgressDialog spinnerDialog = new ProgressDialog(ReserveList.this);
//
//        public NetworkTask(String url, ContentValues values) {
//            this.url = url;
//            this.values = values;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            spinnerDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            spinnerDialog.setMessage("정보를 불러오는 중입니다.");
//            spinnerDialog.show();
//        }
//
//        @Override
//        protected ReservationWork doInBackground(Void... voids) {
//            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
//            JSONObject jsonResult = requestHttpURLConnection.request(url, values);
//
//            ReservationWork reservationwork = jsonToworkInfo(jsonResult);
//            return reservationwork;
//        }
//
//        @Override
//        protected void onPostExecute(ReservationWork reservationwork) {
//            super.onPostExecute(reservationwork);
//            spinnerDialog.dismiss();
//
//            setUserInfo(userInfo);
//            setEnables(false);
//        }
//    }
//
//    private ReservationWork jsonToworkInfo(JSONObject jsonObject) {
//        try {
//            JSONArray jsonArray = jsonObject.getJSONArray("data");
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jobj = jsonArray.getJSONObject(i);
//
//                ReservationWork reservationwork = new ReservationWork(
//                        jobj.getString("amount"),
//                        jobj.getString("myAccount"),
//                        jobj.getString("sendAcount"),
//                        jobj.getString("type")
//
//            );
//            }
//
//            UserInfo userInfo = new UserInfo(
//                    jobj.getString("name"),
//                    jobj.getString("id"),
//                    jobj.getString("password"),
//                    jobj.getString("email"),
//                    jobj.getString("account"),
//                    jobj.getString("carnumber"),
//                    jobj.getString("nfc")
//            );
//            return userInfo;
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
