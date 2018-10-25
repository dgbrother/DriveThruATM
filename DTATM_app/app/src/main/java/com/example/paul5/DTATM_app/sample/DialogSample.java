//package com.example.paul5.DTATM_app.sample;
//
//import android.content.DialogInterface;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.example.paul5.DTATM_app.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DialogSample extends AppCompatActivity implements View.OnClickListener{
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //setContentView(R.layout.reserve_add);
//
//
//        findViewById(R.id.formBtn).setOnClickListener(this);
//        findViewById(R.id.menuBtn).setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.menuBtn:
//                show();
//                break;
//            case R.id.formBtn:
//                finish();
//                break;
//        }
//    }
//
//    void show()
//    {
//        final List<String> ListItems = new ArrayList<>();
//        ListItems.add("입금");
//        ListItems.add("출금");
//        ListItems.add("송금");
//        final CharSequence[] items =  ListItems.toArray(new String[ ListItems.size()]);
//
//        final List SelectedItems  = new ArrayList();
//        int defaultItem = 0;
//        SelectedItems.add(defaultItem);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("예약할 업무를 선택해주세요.");
//        builder.setSingleChoiceItems(items, defaultItem,
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        SelectedItems.clear();
//                        SelectedItems.add(which);
//                    }
//                });
//        builder.setPositiveButton("Ok",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        String msg="";
//
//                        if (!SelectedItems.isEmpty()) {
//                            int index = (int) SelectedItems.get(0);
//                            msg = ListItems.get(index);
//                        }
//                        Toast.makeText(getApplicationContext(),
//                                 msg + "이 선택되었습니다." , Toast.LENGTH_LONG)
//                                .show();
//                    }
//                });
//        builder.setNegativeButton("Cancel",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//        builder.show();
//    }
//
//}
