package com.hafidtech.loketbus.ui.handler;


import android.content.SharedPreferences;

public class MidtransSDK {
    private static SharedPreferences mPreferences = null;


    private MidtransSDK() {
    }



    public static SharedPreferences getmPreferences() {
        return mPreferences;
    }

}
