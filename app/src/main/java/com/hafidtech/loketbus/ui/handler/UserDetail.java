package com.hafidtech.loketbus.ui.handler;

import java.io.Serializable;
import java.util.ArrayList;

public class UserDetail implements Serializable {
    private String userFullName;
    private String email;
    private String phoneNumber;
    private ArrayList<UserAddress> userAddresses;
    private String merchantToken;
    private String userId;

    public UserDetail() {
    }

    public String getUserFullName() {
        return this.userFullName;
    }

    public void setUserFullName(String var1) {
        this.userFullName = var1;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String var1) {
        this.email = var1;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String var1) {
        this.phoneNumber = var1;
    }

    public ArrayList<UserAddress> getUserAddresses() {
        return this.userAddresses;
    }

    public void setUserAddresses(ArrayList<UserAddress> var1) {
        this.userAddresses = var1;
    }

    public String getMerchantToken() {
        return this.merchantToken;
    }

    public void setMerchantToken(String var1) {
        this.merchantToken = var1;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String var1) {
        this.userId = var1;
    }
}