package com.example.paul5.DTATM_app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.paul5.DTATM_app.fragment.ReserveDeposit;
import com.example.paul5.DTATM_app.fragment.ReserveSend;
import com.example.paul5.DTATM_app.fragment.ReserveWithdraw;

public class SubActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences appData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
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

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment, new ReserveSend());
        transaction.commit();
    }

    private SendInfo saveSend() {
        ReserveSend fragment = (ReserveSend) getSupportFragmentManager().findFragmentById(R.id.fragment);
        SendInfo sendInfo = fragment.getSendInfo();
        return sendInfo;
    }

    private WithdrawInfo saveWithdraw() {
        ReserveWithdraw fragment = (ReserveWithdraw) getSupportFragmentManager().findFragmentById(R.id.fragment);
        WithdrawInfo withdrawInfo = fragment.getWithdrawInfo();
        return withdrawInfo;
    }

    private DepositInfo saveDeposit() {
        ReserveDeposit fragment = (ReserveDeposit) getSupportFragmentManager().findFragmentById(R.id.fragment);
        DepositInfo depositInfo = fragment.getDepositInfo();
        return depositInfo;
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
                if(CurrentFragment instanceof ReserveSend) {    // 송금
                    saveSend();
                    Log.d("testing my account", saveSend().getMyAccount());
                    Log.d("testing send account", saveSend().getSendAccount());
                    Log.d("testing amount", saveSend().getAmount());
                }
                if(CurrentFragment instanceof ReserveWithdraw) {    // 출금
                    saveWithdraw();
                    Log.d("testing my account", saveWithdraw().getMyAccount());
                    Log.d("testing amount", saveWithdraw().getAmount());
                }
                if(CurrentFragment instanceof ReserveDeposit) {     // 입금
                    saveDeposit();
                    Log.d("testing my account", saveDeposit().getMyAccount());
                }
                Intent intent = new Intent(SubActivity.this, ReservationMainActivity.class);
                startActivity(intent);
                break;
            case R.id.reserve_cancel:
                Intent intent2 = new Intent(SubActivity.this, ReservationMainActivity.class);
                startActivity(intent2);
                break;
        }
    }

    public void switchFragment(String type) {
        Fragment fragment = null;
        switch (type) {
            case "send":
                fragment = new ReserveSend();
                break;
            case "withdraw":
                fragment = new ReserveWithdraw();
                break;
            case "deposit":
                fragment = new ReserveDeposit();
                break;
        }
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }
}
