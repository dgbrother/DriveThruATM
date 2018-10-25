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
import android.widget.Spinner;

import com.example.paul5.DTATM_app.R;
import com.example.paul5.DTATM_app.ReservationWork;

public class Deposit extends Fragment {
    String selected;

    public static Deposit newInstance(String account) {
        Deposit deposit = new Deposit();
        Bundle args = new Bundle();
        args.putString("account", account);
        deposit.setArguments(args);
        return deposit;
    }

    //입금하기
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reserve_deposit, container, false);
        String[] account = new String[1];
        if(getArguments() != null) {
            account[0] = getArguments().getString("account");
        }

        final Spinner AccountSpinner = (Spinner) v.findViewById(R.id.spinner_account);
        AccountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                selected = (String)AccountSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        ArrayAdapter<String> spinneradapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item);
        spinneradapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        AccountSpinner.setAdapter(spinneradapter);
        spinneradapter.addAll(account);

        return v;
    }

    public ReservationWork getDepositInfo(ReservationWork work) {
        final String Info_selected = this.selected;
        work.setMyAccount(Info_selected);
        return work;
    }
}
