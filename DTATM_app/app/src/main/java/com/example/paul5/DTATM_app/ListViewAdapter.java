package com.example.paul5.DTATM_app;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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

/////////////////////////////
////// 삭제버튼 시도중 //////
/////////////////////////////
        Button deleteBtn = (Button) convertView.findViewById(R.id.delete_button);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad = new AlertDialog.Builder(context);
                ad.setTitle("★★삭제 재확인★★");       // 삭제 다이얼로그 제목 설정
                ad.setMessage("정말 삭제하시겠습니까?");   // 삭제 다이얼로그 내용 설정

                ad.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("testing","예 누름 => 삭제되야함");
                        dialog.dismiss();     //닫기
                        // Event
                    }
                });

                ad.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("testing","아니오 누름 => 삭제안되야함");
                        dialog.dismiss();     //닫기
                        // Event
                    }
                });
                ad.show();
            }
        });
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
