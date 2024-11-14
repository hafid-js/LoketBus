package com.hafidtech.loketbus.ui.main.mybooking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingFragment
import com.bagicode.bagicodebaseutils.utils.Const
import com.google.gson.Gson
import com.hafidtech.loketbus.databinding.FragmentMybookingBinding
import com.hafidtech.loketbus.ui.HafidTechLoketBus
import com.hafidtech.loketbus.ui.main.home.choice.bus.PilihBusAdapter
import com.hafidtech.loketbus.ui.model.response.BusResponse
import com.hafidtech.loketbus.ui.model.response.LoginResponse
import com.hafidtech.loketbus.ui.model.response.MyBookingResponse

class MyBookingFragment : BaseBindingFragment(), MyBookingAdapter.ItemAdapterCallback, MyBookingContract.View {

    private lateinit var binding : FragmentMybookingBinding
    private lateinit var presenter: MyBookingPresenter

    override fun getFragmentView(): ViewBinding {
        binding = FragmentMybookingBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        presenter = MyBookingPresenter(this)

        var user = HafidTechLoketBus.getApp().getUser()
        var userResponse = Gson().fromJson(user, LoginResponse::class.java)

        userResponse.idUser?.let {
            presenter.getMyBookingList(it)
        }

    }

    override fun onMyBookingSuccess(response: ArrayList<MyBookingResponse>) {
       var adapter = MyBookingAdapter(response, this)
        var layoutManager = LinearLayoutManager(activity)
        binding.rvMybooking.layoutManager = layoutManager
        binding.rvMybooking.adapter = adapter
    }

    override fun onMyBookingFailed(message: String) {
        showSnackbarMessage(binding.rvMybooking, message, Const.ToastType.Error)
    }



    override fun onListMyBookingClick(v: View, data: MyBookingResponse) {
    }
}