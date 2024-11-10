package com.hafidtech.loketbus.ui.main.home.choice.bus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingFragment
import com.bagicode.bagicodebaseutils.utils.Const
import com.hafidtech.loketbus.R
import com.hafidtech.loketbus.databinding.FragmentPilihBusBinding
import com.hafidtech.loketbus.ui.dialog.bottomsheet.ListTerminalBottomSheet
import com.hafidtech.loketbus.ui.dialog.bottomsheet.ListTipeBusBottomSheet
import com.hafidtech.loketbus.ui.model.BusRequest
import com.hafidtech.loketbus.ui.model.TerminalModel
import com.hafidtech.loketbus.ui.model.response.BusResponse

class PilihBusFragment : BaseBindingFragment(), PilihBusAdapter.ItemAdapterCallback, PilihBusContract.View {

    private var binding : FragmentPilihBusBinding?=null
    private lateinit var presenter : PilihBusPresenter
    private lateinit var busRequest: BusRequest

    override fun getFragmentView(): ViewBinding {
        if (binding == null) {
            binding = FragmentPilihBusBinding.inflate(layoutInflater)
        }
        return binding as FragmentPilihBusBinding
    }

    override fun onBindView() {
        presenter = PilihBusPresenter(this)
         var busRequestArgs = arguments?.getParcelable<BusRequest>("data")
        busRequestArgs?.let {
            presenter.getBusList(it)
            busRequest = it
        }

        var dataTipeBus = ArrayList<String>()
        dataTipeBus.add("Semua")
        dataTipeBus.add("Bisnis")
        dataTipeBus.add("Ekonomi")

        binding?.ivFilter?.setOnClickListener{
            ListTipeBusBottomSheet.newInstance(object : ListTipeBusBottomSheet.Listener {
                override fun onClick(data: String) {
                    presenter.getBusList(busRequest.apply {
                        tipe = data
                    })
                }
            }, 0,
                dataTipeBus,
                "Filter Tipe Bus",
                "Gunakan filter untuk mempermudah pencarian")
                .show(parentFragmentManager, "")
        }
    }

    override fun onBusSuccess(busResponse: ArrayList<BusResponse>) {
        var adapter = PilihBusAdapter(busResponse, this)
        val layoutManager = LinearLayoutManager(activity)

        binding?.rvBus?.layoutManager = layoutManager
        binding?.rvBus?.adapter = adapter
    }

    override fun onBusFailed(message: String) {
        binding?.rvBus?.let { showSnackbarMessage(it, message, Const.ToastType.Error) }
    }

    override fun onListPilihBusClick(v: View, data: BusResponse) {
        var bundle = Bundle()
        bundle.putParcelable("data", data)
        bundle.putParcelable("dataPick", busRequest)
        Navigation.findNavController(v)
            .navigate(R.id.fragmentPilihKursi, bundle)
    }
}