package com.hafidtech.loketbus.ui.main.home.choice.tiket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingFragment
import com.hafidtech.loketbus.R
import com.hafidtech.loketbus.databinding.FragmentPilihKursiBinding

class PilihKursiFragment : BaseBindingFragment() {

    lateinit var binding : FragmentPilihKursiBinding

    override fun getFragmentView(): ViewBinding {
        binding = FragmentPilihKursiBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {

    }
}