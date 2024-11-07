package com.hafidtech.loketbus.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BusRequest (
   var tipe : String?="",
   var penumpang : Int?=0,
   var date : String?="",
   var dari : String?="",
   var dariCode : String?="",
   var tujuan : String?="",
   var tujuanCode : String?=""
) : Parcelable