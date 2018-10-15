package com.example.paul5.DTATM_app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.paul5.DTATM_app.fragment.ReserveDeposit;
import com.example.paul5.DTATM_app.fragment.ReserveSend;
import com.example.paul5.DTATM_app.fragment.ReserveWithdraw;

public class SubActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

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

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.reserve_send_button:
                switchFragment("send");
                break;
            case R.id.reserve_withdraw_button:
                switchFragment("withdraw");
                break;
            case R.id.reserve_deposit_button:
                switchFragment("deposit");
                break;
            case R.id.reserve_save:
                break;
            case R.id.reserve_cancel:
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
                fragment = new ReserveDeposit();
                break;
            case "deposit":
                fragment = new ReserveWithdraw();
                break;
        }
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }
}
