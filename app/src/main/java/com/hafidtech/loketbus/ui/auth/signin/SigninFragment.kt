package com.hafidtech.loketbus.ui.auth.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingFragment
import com.bagicode.bagicodebaseutils.utils.changePage
import com.hafidtech.loketbus.R
import com.hafidtech.loketbus.databinding.FragmentSigninBinding
import com.hafidtech.loketbus.ui.main.MainActivity


class SigninFragment : BaseBindingFragment() {

    private lateinit var binding : FragmentSigninBinding


    override fun getFragmentView(): ViewBinding {
        binding = FragmentSigninBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        binding.btnSigin.setOnClickListener{
            changePage(MainActivity::class.java, null, activity, true)
        }
    }

}