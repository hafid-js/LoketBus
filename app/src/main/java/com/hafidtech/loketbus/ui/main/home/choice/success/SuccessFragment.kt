package com.hafidtech.loketbus.ui.main.home.choice.success

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.base.BaseFragment
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingFragment
import com.hafidtech.loketbus.R
import com.hafidtech.loketbus.databinding.FragmentSuccessBinding

class SuccessFragment : BaseBindingFragment() {

    lateinit var binding: FragmentSuccessBinding

    override fun getFragmentView(): ViewBinding {
        binding = FragmentSuccessBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        binding.btnSuccess.setOnClickListener{
            requireActivity().finish()
        }
    }
}