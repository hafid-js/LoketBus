package com.hafidtech.loketbus.ui.main.home.choice.bus

import com.bagicode.bagicodebaseutils.base.BasePresenter
import com.bagicode.bagicodebaseutils.base.BaseView
import com.hafidtech.loketbus.ui.model.BusRequest
import com.hafidtech.loketbus.ui.model.response.BusResponse
import com.hafidtech.loketbus.ui.model.response.LoginResponse

interface PilihBusContract {

    interface View : BaseView {
        fun onBusSuccess(busResponse: ArrayList<BusResponse>)
        fun onBusFailed(message: String)
    }

    interface Presenter : PilihBusContract, BasePresenter {
        fun getBusList(busRequest: BusRequest)
    }
}