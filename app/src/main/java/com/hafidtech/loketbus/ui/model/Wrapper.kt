package com.hafidtech.loketbus.ui.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Wrapper<T>(
    @Expose
    @SerializedName("codeMessage")
    var codeMessage: String?,
    @Expose
    @SerializedName("codeStatus")
    var codeStatus: String?,
    @Expose
    @SerializedName("data")
    var data: T? = null
)