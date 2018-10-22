package com.example.paul5.DTATM_app;

public class SendInfo {
    String MyAccount;
    String SendAccount;
    String amount;

    public SendInfo(String MyAccount, String SendAccount, String amount) {
        this.MyAccount = MyAccount;
        this.SendAccount = SendAccount;
        this.amount = amount;
    }

    public String getMyAccount() { return MyAccount; }

    public void setMyAccount(String MyAccount) {
        this.MyAccount = MyAccount;
    }

    public String getSendAccount() {
        return SendAccount;
    }

    public void setSendAccount(String SendAccount) {
        this.SendAccount = SendAccount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
