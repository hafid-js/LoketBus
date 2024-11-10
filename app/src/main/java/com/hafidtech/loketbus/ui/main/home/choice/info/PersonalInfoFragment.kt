package com.hafidtech.loketbus.ui.main.home.choice.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingFragment
import com.hafidtech.loketbus.R
import com.hafidtech.loketbus.databinding.FragmentPersonalInfoBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PersonalInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PersonalInfoFragment : BaseBindingFragment() {

    private lateinit var binding : FragmentPersonalInfoBinding

    override fun getFragmentView(): ViewBinding {
        binding = FragmentPersonalInfoBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        TODO("Not yet implemented")
    }
}