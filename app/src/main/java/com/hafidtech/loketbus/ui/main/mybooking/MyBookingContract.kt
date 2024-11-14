package com.hafidtech.loketbus.ui.main.mybooking

import com.bagicode.bagicodebaseutils.base.BasePresenter
import com.bagicode.bagicodebaseutils.base.BaseView
import com.hafidtech.loketbus.ui.model.BusRequest
import com.hafidtech.loketbus.ui.model.response.BusResponse
import com.hafidtech.loketbus.ui.model.response.LoginResponse
import com.hafidtech.loketbus.ui.model.response.MyBookingResponse

interface MyBookingContract {

    interface View : BaseView {
        fun onMyBookingSuccess(response: ArrayList<MyBookingResponse>)
        fun onMyBookingFailed(message: String)
    }

    interface Presenter : MyBookingContract, BasePresenter {
        fun getMyBookingList(idUser: String)
    }
}