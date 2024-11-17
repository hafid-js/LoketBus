package com.hafidtech.loketbus.ui.handler;

import java.io.Serializable;

public class UserAddress implements Serializable {
    private String address;
    private String city;
    private String zipcode;
    private String country;
    private int addressType;

    public UserAddress() {
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String var1) {
        this.address = var1;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String var1) {
        this.city = var1;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(String var1) {
        this.zipcode = var1;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String var1) {
        this.country = var1;
    }

    public int getAddressType() {
        return this.addressType;
    }

    public void setAddressType(int var1) {
        this.addressType = var1;
    }
}
