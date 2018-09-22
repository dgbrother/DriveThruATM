/*
package com.example.jwho_.atmapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ReservationWork> listViewItemList = new ArrayList<>();

    public ListViewAdapter() {
        // 데이터 가져오기
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.reserve_list_item, parent, false);
        }

        TextView businessName = convertView.findViewById(R.id.businessname);
        ReservationWork item = listViewItemList.get(position);
        businessName.setText(item.getBusinessName());

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

    public void addItem(String name) {
        ReservationWork work = new ReservationWork();
        work.setBusinessName(name);
        listViewItemList.add(work);
    }
}
*/