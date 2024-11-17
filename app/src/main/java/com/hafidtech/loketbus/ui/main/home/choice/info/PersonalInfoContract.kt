package com.hafidtech.loketbus.ui.main.home.choice.info

import android.view.View
import com.bagicode.bagicodebaseutils.base.BasePresenter
import com.bagicode.bagicodebaseutils.base.BaseView
import com.hafidtech.loketbus.ui.model.request.CheckoutRequest

interface PersonalInfoContract {

    interface View : BaseView {
        fun onCheckoutBookingSuccess(id:String, view: android.view.View)
        fun onCheckoutUpdateSuccess(message: String, view: android.view.View)
        fun onCheckoutUpdateFailed(message: String)
        fun onCheckoutFailed(message : String)
    }

    interface Presenter : PersonalInfoContract, BasePresenter {
        fun setCheckoutBooking (checkoutRequest: CheckoutRequest, view: android.view.View)
        fun setCheckoutUpdate (idTiket:String, statusPembayaran:String, view: android.view.View)
    }
}