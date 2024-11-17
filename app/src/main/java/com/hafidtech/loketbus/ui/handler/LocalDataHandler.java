package com.hafidtech.loketbus.ui.handler;

import android.content.SharedPreferences;

import com.google.gson.Gson;

public class LocalDataHandler {
    public LocalDataHandler() {
    }

    private static String convertObjectIntoJson(Object var0) {
        Gson var1 = new Gson();
        return var1.toJson(var0);
    }

    private static <T> T convertBackToObject(String var0, Class<T> var1) {
        Gson var2 = new Gson();
        return var2.fromJson(var0, var1);
    }

    public static void saveString(String var0, String var1) {
        SharedPreferences var2 = MidtransSDK.getmPreferences();
        if (var2 != null) {
            var2.edit().putString(var0, var1).apply();
        }

    }

    public static String readString(String var0) {
        SharedPreferences var1 = MidtransSDK.getmPreferences();
        return var1 == null ? null : var1.getString(var0, "");
    }

    public static void saveObject(String var0, Object var1) {
        SharedPreferences var2 = MidtransSDK.getmPreferences();
        if (var2 != null) {
            var2.edit().putString(var0, convertObjectIntoJson(var1)).apply();
        }

    }

    public static <T> T readObject(String var0, Class<T> var1) {
        SharedPreferences var2 = MidtransSDK.getmPreferences();
        return var2 == null ? null : convertBackToObject(var2.getString(var0, ""), var1);
    }
}
