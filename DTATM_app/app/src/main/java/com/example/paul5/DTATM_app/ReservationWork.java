package com.example.paul5.DTATM_app;

import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReservationWork {
    // 리스트뷰에 들어갈 업무 내역 부분
    private String no;
    private String type;
    private String id;
    private String carNumber;
    private String srcAccount;
    private String dstAccount;
    private String amount;
    private String isdone;

    public ReservationWork() {
        this.no = "-";
        this.type = "-";
        this.id = "-";
        this.carNumber = "-";
        this.srcAccount = "-";
        this.dstAccount = "-";
        this.amount = "-";
        this.isdone = "-";
    }

    public ReservationWork(String no, String type, String id, String carNumber, String srcAccount, String sendAccount, String amount, String isdone) {
        this.no = no;
        this.type = type;
        this.id = id;
        this.carNumber = carNumber;
        this.srcAccount = srcAccount;
        this.dstAccount = sendAccount;
        this.amount = amount;
        this.isdone = isdone;
    }

    public static ArrayList<ReservationWork> jsonToReserveInfo(JSONObject jsonMain) {
        try {
            JSONArray jarray = jsonMain.getJSONArray("data");
            ArrayList<ReservationWork> reservationWorks = new ArrayList<>();
            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jsonObject = jarray.getJSONObject(i);  // JSONObject 추출
                ReservationWork reserveInfo = new ReservationWork(
                        jsonObject.getString("no"),
                        jsonObject.getString("type"),
                        jsonObject.getString("id"),
                        jsonObject.getString("carnumber"),
                        jsonObject.getString("src_account"),
                        jsonObject.getString("dst_account"),
                        jsonObject.getString("amount"),
                        jsonObject.getString("isdone")
                );
                reservationWorks.add(reserveInfo);
            }
            return reservationWorks;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getSrcAccount() {
        return srcAccount;
    }

    public void setSrcAccount(String srcAccount) {
        this.srcAccount = srcAccount;
    }

    public String getDstAccount() {
        return dstAccount;
    }

    public void setDstAccount(String dstAccount) {
        this.dstAccount = dstAccount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsdone() {
        return isdone;
    }

    public void setIsdone(String isdone) {
        this.isdone = isdone;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
