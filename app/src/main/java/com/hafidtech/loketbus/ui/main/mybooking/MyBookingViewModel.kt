package com.hafidtech.loketbus.ui.main.mybooking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyBookingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is My Bookings Fragment"
    }
    val text: LiveData<String> = _text
}