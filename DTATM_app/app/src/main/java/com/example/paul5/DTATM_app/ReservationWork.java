package com.example.paul5.DTATM_app;

import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReservationWork {
    // 리스트뷰에 들어갈 업무 내역 부분
    private String businessName;
    private String amount;
    private String carnumber;
    private String myAccount;
    private String sendAccount;
    private String id;
    private String type;
    private String isdone;

    public ReservationWork(String businessName, String amount, String carnumber, String myAccount,String sendAccount, String id, String type, String isdone ) {
        this.businessName = businessName;
        this.amount = amount;
        this.carnumber = carnumber;
        this.myAccount = myAccount;
        this.sendAccount = sendAccount;
        this.id = id;
        this.type = type;
        this.isdone = isdone;
    }

    public static ArrayList<ReservationWork> jsonToReserveInfo(JSONObject jsonMain) {
        try {
            JSONArray jarray = jsonMain.getJSONArray("data");
            ArrayList<ReservationWork> reservationWorks = new ArrayList<>();
            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jsonObject = jarray.getJSONObject(i);  // JSONObject 추출
                ReservationWork reserveInfo = new ReservationWork(
                        jsonObject.getString("type"),
                        jsonObject.getString("amount"),
                        jsonObject.getString("carnumber"),
                        jsonObject.getString("src_account"),
                        jsonObject.getString("dst_account"),
                        jsonObject.getString("id"),
                        jsonObject.getString("type"),
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

    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber;
    }

    public String getMyAccount() {
        return myAccount;
    }

    public void setMyAccount(String myAccount) {
        this.myAccount = myAccount;
    }

    public String getSendAccount() {
        return sendAccount;
    }

    public void setSendAccount(String sendAccount) {
        this.sendAccount = sendAccount;
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

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessName() {
        return businessName;
    }
}
