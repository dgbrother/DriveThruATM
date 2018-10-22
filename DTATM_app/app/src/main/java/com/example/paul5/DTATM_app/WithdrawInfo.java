package com.example.paul5.DTATM_app;

public class WithdrawInfo {
    String MyAccount;
    String amount;

    public WithdrawInfo(String MyAccount, String amount) {
        this.MyAccount = MyAccount;
        this.amount = amount;
    }

    public String getMyAccount() { return MyAccount; }

    public void setMyAccount(String MyAccount) {
        this.MyAccount = MyAccount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
