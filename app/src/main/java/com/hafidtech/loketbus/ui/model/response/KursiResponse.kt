package com.hafidtech.loketbus.ui.model.response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class KursiResponse(
    @Expose
    @SerializedName("checkKursi")
    var checkKursi: Boolean?,
    @Expose
    @SerializedName("id")
    var id: String?,
    @Expose
    @SerializedName("nameKursi")
    var nameKursi: String?,
    @Expose
    @SerializedName("statusKursi")
    var statusKursi: Boolean?
)