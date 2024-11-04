package com.hafidtech.loketbus.ui.auth.signin

import com.bagicode.bagicodebaseutils.base.BasePresenter
import com.bagicode.bagicodebaseutils.base.BaseView
import com.hafidtech.loketbus.ui.model.response.LoginResponse

interface SigninContract {

    interface View : BaseView {
        fun onSigninSuccess(loginResponse: LoginResponse)
        fun onSigninFailed(message: String)
    }

    interface Presenter : SigninContract, BasePresenter {
        fun setSignin(email: String, pass: String)
    }
}