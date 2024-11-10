package com.hafidtech.loketbus.ui.main.home.choice.tiket

import com.bagicode.bagicodebaseutils.base.BasePresenter
import com.bagicode.bagicodebaseutils.base.BaseView
import com.hafidtech.loketbus.ui.model.BusRequest
import com.hafidtech.loketbus.ui.model.response.BusResponse
import com.hafidtech.loketbus.ui.model.response.KursiResponse
import com.hafidtech.loketbus.ui.model.response.LoginResponse

interface PilihKursiContract {

    interface View : BaseView {
        fun onKursiSuccess(response: ArrayList<KursiResponse>)
        fun onKursiFailed(message: String)
    }

    interface Presenter : PilihKursiContract, BasePresenter {
        fun getKursiList(id : String)
    }
}