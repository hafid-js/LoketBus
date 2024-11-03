package com.hafidtech.loketbus.ui

import android.content.SharedPreferences
import androidx.multidex.MultiDexApplication
import androidx.preference.PreferenceManager
import com.hafidtech.loketbus.ui.network.HttpClient

class LoketBusApp : MultiDexApplication() {

    companion object {
        lateinit var instance : LoketBusApp
        fun getApp() : LoketBusApp {
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
        getPreferences().edit().putString("PREFERENCE_TOKEN", token)
        HttpClient.getInstance().buildRetrofitClient(token)
    }

    fun getToken() : String? {
        return getPreferences().getString("PREFERENCE_TOKEN", null)
    }

    fun setUser(user : String) {
        getPreferences().edit().putString("PREFERENCE_USER", user)
    }

    fun getUser() : String? {
        return getPreferences().getString("PREFERENCE_USER", null)
    }

    fun clearToken() {
        getPreferences().edit().remove("PREFERENCE_TOKEN").apply()
        HttpClient.getInstance().buildRetrofitClient("")
    }
}