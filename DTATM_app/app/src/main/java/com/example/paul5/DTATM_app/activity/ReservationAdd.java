package com.example.paul5.DTATM_app.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.paul5.DTATM_app.R;
import com.example.paul5.DTATM_app.RequestHttpURLConnection;
import com.example.paul5.DTATM_app.ReservationWork;
import com.example.paul5.DTATM_app.fragment.Deposit;
import com.example.paul5.DTATM_app.fragment.Send;
import com.example.paul5.DTATM_app.fragment.Withdraw;

public class ReservationAdd extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences appData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_add);
        appData = getSharedPreferences("appData", MODE_PRIVATE);
        String account = appData.getString("account", null);

        Fragment fragmentTransmit = new Fragment();
        Bundle bundle = new Bundle(1);
        bundle.putString("account", account);
        fragmentTransmit.setArguments(bundle);

        findViewById(R.id.reserve_send_button)      .setOnClickListener(this);
        findViewById(R.id.reserve_withdraw_button)  .setOnClickListener(this);
        findViewById(R.id.reserve_deposit_button)   .setOnClickListener(this);
        findViewById(R.id.reserve_save)             .setOnClickListener(this);
        findViewById(R.id.reserve_cancel)           .setOnClickListener(this);

        appData = getSharedPreferences("appData", MODE_PRIVATE);
        String currentUserAccount = appData.getString("account", "none");

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment, new Send().newInstance(currentUserAccount));
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.reserve_send_button:  // 송금
                switchFragment("send");
                break;

            case R.id.reserve_withdraw_button:  // 출금
                switchFragment("withdraw");
                break;

            case R.id.reserve_deposit_button:   //입금
                switchFragment("deposit");
                break;

            case R.id.reserve_save:
                Fragment CurrentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment);
                ReservationWork work = null;
                if(CurrentFragment instanceof Send)
                    work = getReservationWork(CurrentFragment, "send");

                if(CurrentFragment instanceof Withdraw)
                    work = getReservationWork(CurrentFragment, "withdraw");

                if(CurrentFragment instanceof Deposit)
                    work = getReservationWork(CurrentFragment, "deposit");

                String BASE_URL = "http://35.200.117.1:8080/control.jsp";
                ContentValues param = ReservationWorkToParam(work);

                NetworkTask insertReservationTask = new NetworkTask(BASE_URL, param);
                insertReservationTask.execute();
                break;

            case R.id.reserve_cancel:
                Intent intent2 = new Intent(ReservationAdd.this, ReservationMain.class);
                startActivity(intent2);
                break;
        }
    }

    private ReservationWork getReservationWork(Fragment currentFragment, String type) {
        ReservationWork work = new ReservationWork();
        work.setId          (appData.getString("id", "none"));
        work.setMyAccount   (appData.getString("account", "none"));
        work.setCarnumber   (appData.getString("carNumber","none"));

        switch(type) {
            case "send":
                Send sendFragment = (Send)currentFragment;
                work.setBusinessName("send");
                work = sendFragment.getSendInfo(work);
                break;
            case "withdraw":
                Withdraw withdrawFragment = (Withdraw)currentFragment;
                work.setBusinessName("withdraw");
                work = withdrawFragment.getWithdrawInfo(work);
                break;
            case "deposit":
                Deposit depositFragment = (Deposit)currentFragment;
                work.setBusinessName("deposit");
                work = depositFragment.getDepositInfo(work);
                break;
        }
        return work;
    }

    public void switchFragment(String type) {
        Fragment fragment = null;
        String currentUserAccount = appData.getString("account", "none");
        switch (type) {
            case "send":
                fragment = new Send().newInstance(currentUserAccount);
                break;
            case "withdraw":
                fragment = new Withdraw().newInstance(currentUserAccount);
                break;
            case "deposit":
                fragment = new Deposit().newInstance(currentUserAccount);
                break;
        }
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }

    private ContentValues ReservationWorkToParam(ReservationWork work) {
        ContentValues params = new ContentValues();
        params.put("type",          "reservation");
        params.put("action",        "insert");
        params.put("bankingType",   work.getBusinessName());
        params.put("id",            work.getId());
        params.put("carNumber",     work.getCarnumber());
        params.put("src_account",   work.getMyAccount());
        params.put("dst_account",   work.getSendAccount());
        params.put("amount",        work.getAmount());

        return params;
    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {
        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {
            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... voids) {
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            requestHttpURLConnection.request(url, values);

            return "ok";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Intent intent = new Intent(ReservationAdd.this, ReservationMain.class);
            startActivity(intent);
        }
    }
}
