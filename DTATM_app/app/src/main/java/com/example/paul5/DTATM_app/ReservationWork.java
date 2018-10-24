package com.example.paul5.DTATM_app;

public class ReservationWork {
    // 리스트뷰에 들어갈 업무 내역 부분
    private String businessName;
    private String amount;
    private String carnumber;
    private String myAccount;
    private String sendAccount;
    private String id;
    private String isdone;

    public ReservationWork() {
        businessName = "-";
        amount = "-";
        carnumber = "-";
        myAccount = "-";
        sendAccount = "-";
        id = "-";
        isdone = "-";
    }

    public ReservationWork(String businessName, String amount, String carnumber, String myAccount, String sendAccount, String id, String type, String isdone) {
        this.businessName = businessName;
        this.amount = amount;
        this.carnumber = carnumber;
        this.myAccount = myAccount;
        this.sendAccount = sendAccount;
        this.id = id;
        this.isdone = isdone;
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
