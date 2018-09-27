package com.example.paul5.DTATM_app;

public class UserInfo {
    String name;
    String id;
    String password;
    String email;
    String account;
    String carNumber;
    String nfcId;

    public UserInfo(String name, String id, String password, String email, String account, String carNumber, String nfcId) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.email = email;
        this.account = account;
        this.carNumber = carNumber;
        this.nfcId = nfcId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", account='" + account + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", nfcId='" + nfcId + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getNfcId() {
        return nfcId;
    }

    public void setNfcId(String nfcId) {
        this.nfcId = nfcId;
    }
}
