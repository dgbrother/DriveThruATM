package com.example.paul5.DTATM_app;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ReservationWork> listViewItemList = new ArrayList<>();

    public ListViewAdapter() {
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.reserve_main_listitem, parent, false);
        }

        //TextView no         = convertView.findViewById(R.id.no);
        TextView type       = convertView.findViewById(R.id.type);
        TextView srcAccount = convertView.findViewById(R.id.src_account);
        TextView dstAccount = convertView.findViewById(R.id.dst_account);
        TextView amount     = convertView.findViewById(R.id.amount);

        ReservationWork item = listViewItemList.get(position);

        String bankingType = item.getType();
        switch(bankingType) {
            case "send"     : bankingType = "송금"; break;
            case "withdraw" : bankingType = "출금"; break;
            case "deposit"  : bankingType = "입금"; break;
        }
        
        //no          .setText(item.getNo());
        type        .setText(bankingType);
        srcAccount  .setText(item.getSrcAccount());
        dstAccount  .setText(item.getDstAccount());
        amount      .setText(item.getAmount());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    public void addItem(ReservationWork RW) {
        listViewItemList.add(RW);
    }

    public String getNo(int position) { return listViewItemList.get(position).getNo(); }

}
