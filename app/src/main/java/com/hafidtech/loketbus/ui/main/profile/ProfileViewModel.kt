package com.hafidtech.loketbus.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.hafidtech.loketbus.ui.HafidTechLoketBus
import com.hafidtech.loketbus.ui.model.response.LoginResponse

class ProfileViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        var user = HafidTechLoketBus.getApp().getUser()
        var userResponse = Gson().fromJson(user, LoginResponse::class.java)
        value = "This is Profile Fragment ${userResponse.email}"
    }
    val text: LiveData<String> = _text
}