package com.hafidtech.loketbus.ui.main.home.choice.bus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingFragment
import com.hafidtech.loketbus.R
import com.hafidtech.loketbus.databinding.FragmentPilihBusBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PilihBusFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PilihBusFragment : BaseBindingFragment() {

    private lateinit var binding : FragmentPilihBusBinding

    override fun getFragmentView(): ViewBinding {
        binding = FragmentPilihBusBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {

    }
}