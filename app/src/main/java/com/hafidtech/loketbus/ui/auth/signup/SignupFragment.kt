package com.hafidtech.loketbus.ui.auth.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingFragment
import com.bagicode.bagicodebaseutils.utils.Const
import com.hafidtech.loketbus.R
import com.hafidtech.loketbus.databinding.FragmentSigninBinding
import com.hafidtech.loketbus.databinding.FragmentSignupBinding


class SignupFragment : BaseBindingFragment(), SignupContract.View {

    lateinit var binding : FragmentSignupBinding
    lateinit var presenter: SignupPresenter

    override fun getFragmentView(): ViewBinding {
        binding = FragmentSignupBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        presenter = SignupPresenter(this)

        binding.btnRegister.setOnClickListener {
            var usernameParms = binding.etUsername.text.toString()
            var emailParms = binding.etEmail.text.toString()
            var passParms = binding.etPassword.text.toString()

            if (usernameParms.isNullOrEmpty()) {
                showSnackbarMessage(binding.btnRegister, "Silahkan isi username", Const.ToastType.Error)
            } else if (emailParms.isNullOrEmpty()) {
            showSnackbarMessage(binding.btnRegister, "Silahkan isi email", Const.ToastType.Error)
            } else if (passParms.isNullOrEmpty()) {
                showSnackbarMessage(binding.btnRegister, "Silahkan isi password", Const.ToastType.Error)
            } else {
                presenter.setSignup(
                    emailParms,
                    passParms,
                    usernameParms
                )
            }
        }
    }

    override fun onSignupSuccess(message: String) {
        showSnackbarMessage(binding.btnRegister, message, Const.ToastType.Success)

        binding.etUsername.setText("")
        binding.etEmail.setText("")
        binding.etPassword.setText("")
    }

    override fun onSignupFailed(message: String) {
        showSnackbarMessage(binding.btnRegister, message, Const.ToastType.Error)
    }
}