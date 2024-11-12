package com.hafidtech.loketbus.ui

import android.content.SharedPreferences
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import androidx.preference.PreferenceManager
//import androidx.preference.PreferenceManager
import com.hafidtech.loketbus.ui.network.HttpClient

class HafidTechLoketBus : MultiDexApplication() {
    companion object {
        lateinit var instance : HafidTechLoketBus
        fun getApp() : HafidTechLoketBus {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getPreferences() : SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(this)
    }

    fun setToken(token : String) {
        getPreferences().edit().putString("PREFERENCE_TOKEN", token).apply()
        HttpClient.getInstance().buildRetrofitClient(token)
    }

    fun getToken() : String? {
        return getPreferences().getString("PREFERENCE_TOKEN", null)
    }

    fun setUser(user : String) {
        getPreferences().edit().putString("PREFERENCE_USER", user).apply()
    }

    fun getUser() : String? {
        return getPreferences().getString("PREFERENCE_USER", null)
    }

    fun clearToken() {
        getPreferences().edit().remove("PREFERENCE_TOKEN").apply()
        HttpClient.getInstance().buildRetrofitClient("")
    }

}