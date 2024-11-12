package com.hafidtech.loketbus.ui.auth.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingFragment
import com.bagicode.bagicodebaseutils.utils.Const
import com.bagicode.bagicodebaseutils.utils.changePage
import com.google.gson.Gson
import com.hafidtech.loketbus.R
import com.hafidtech.loketbus.databinding.FragmentSigninBinding
import com.hafidtech.loketbus.ui.HafidTechLoketBus
import com.hafidtech.loketbus.ui.main.MainActivity
import com.hafidtech.loketbus.ui.model.response.LoginResponse


class SigninFragment : BaseBindingFragment(), SigninContract.View {

    private lateinit var binding : FragmentSigninBinding
    private lateinit var presenter: SigninPresenter


    override fun getFragmentView(): ViewBinding {
        binding = FragmentSigninBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        presenter = SigninPresenter(this)
        binding.btnSigin.setOnClickListener{

            var emailParms = binding.etEmail.text.toString()
            var passParms = binding.etPassword.text.toString()

            if (emailParms.isNullOrEmpty()) {
                showSnackbarMessage(binding.btnSigin, "Silahkan isi email", Const.ToastType.Error)
            } else if (passParms.isNullOrEmpty()) {
                showSnackbarMessage(binding.btnSigin, "Silahkan isi password", Const.ToastType.Error)
            } else {
                presenter.setSignin(
                    emailParms,
                    passParms,
                )
            }
        }
    }

    override fun onSigninSuccess(loginResponse: LoginResponse) {
        HafidTechLoketBus.getApp().setToken(loginResponse?.key!!)

        val gson = Gson()
        val json = gson.toJson(loginResponse)

        HafidTechLoketBus.getApp().setUser(json)

        println(loginResponse.email)
        println(loginResponse.username)

        println("email password ${loginResponse.email} ${loginResponse.username}")

        changePage(MainActivity::class.java, null, activity, true)
    }

    override fun onSigninFailed(message: String) {
        showSnackbarMessage(binding.btnSigin, message, Const.ToastType.Error)
    }

}