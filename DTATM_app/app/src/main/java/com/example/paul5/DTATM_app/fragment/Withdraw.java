package com.example.paul5.DTATM_app.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.paul5.DTATM_app.R;
import com.example.paul5.DTATM_app.ReservationWork;

public class Withdraw extends Fragment {
    String selected;
    EditText eWithdrawAmount;

    public static Withdraw newInstance(String account) {
        Withdraw withdraw = new Withdraw();
        Bundle args = new Bundle();
        args.putString("account", account);
        withdraw.setArguments(args);
        return withdraw;
    }

    //출금하기
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reserve_withdraw, container, false);
        String[] account = new String[1];
        if(getArguments() != null) {
            account[0] = getArguments().getString("account");
        }

        final Spinner AccountSpinner = v.findViewById(R.id.spinner_account);
        AccountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                selected = AccountSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        ArrayAdapter<String> spinneradapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item);
        spinneradapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        AccountSpinner.setAdapter(spinneradapter);
        spinneradapter.addAll(account);

        eWithdrawAmount = v.findViewById(R.id.input_amount);

        return v;
    }

    public ReservationWork getWithdrawInfo(ReservationWork work) {
        final String Info_selected = this.selected;
        final String Info_WithdrawAmount = this.eWithdrawAmount.getText().toString();

        work.setSrcAccount(Info_selected);
        work.setAmount(Info_WithdrawAmount);

        return work;
    }
}
