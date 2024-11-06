package com.hafidtech.loketbus.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingFragment
import com.hafidtech.loketbus.R
import com.hafidtech.loketbus.databinding.FragmentHomeBinding
import com.hafidtech.loketbus.ui.dialog.bottomsheet.ListTerminalBottomSheet
import com.hafidtech.loketbus.ui.model.TerminalModel

class HomeFragment : BaseBindingFragment() {

    private lateinit var binding : FragmentHomeBinding
    private var dataDari = ArrayList<TerminalModel>()

    override fun getFragmentView(): ViewBinding {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        initData()
        binding.ivDari.setOnClickListener {

            ListTerminalBottomSheet.newInstance(
                object : ListTerminalBottomSheet.Listener {
                    override fun onClick(data: TerminalModel) {
                        binding.tvDariValue.text = data.namaTerminal
                    }
                },
                0, dataDari, "Pilih keberangkatan", "Silahkan sesuaikan terminal keberangkatan Anda"
            ).show(parentFragmentManager, "")
        }
    }
    private fun initData() {
        dataDari.add(TerminalModel("Terminal Pulo Gebang", "PGB"))
        dataDari.add(TerminalModel("Terminal Kutoarjo", "KTJ"))
    }
}