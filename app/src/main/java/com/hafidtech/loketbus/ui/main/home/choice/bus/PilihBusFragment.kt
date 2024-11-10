package com.hafidtech.loketbus.ui.main.home.choice.bus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingFragment
import com.bagicode.bagicodebaseutils.utils.Const
import com.hafidtech.loketbus.R
import com.hafidtech.loketbus.databinding.FragmentPilihBusBinding
import com.hafidtech.loketbus.ui.model.BusRequest
import com.hafidtech.loketbus.ui.model.response.BusResponse

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PilihBusFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PilihBusFragment : BaseBindingFragment(), PilihBusAdapter.ItemAdapterCallback, PilihBusContract.View {

    private lateinit var binding : FragmentPilihBusBinding
    private lateinit var presenter : PilihBusPresenter
    private lateinit var busRequest: BusRequest

    override fun getFragmentView(): ViewBinding {
        binding = FragmentPilihBusBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        presenter = PilihBusPresenter(this)
         var busRequestArgs = arguments?.getParcelable<BusRequest>("data")
        busRequestArgs?.let {
            presenter.getBusList(it)
            busRequest = it
        }
    }

    override fun onBusSuccess(busResponse: ArrayList<BusResponse>) {
        var adapter = PilihBusAdapter(busResponse, this)
        val layoutManager = LinearLayoutManager(activity)

        binding.rvBus.layoutManager = layoutManager
        binding.rvBus.adapter = adapter
    }

    override fun onBusFailed(message: String) {
        showSnackbarMessage(binding.rvBus, message, Const.ToastType.Error)
    }

    override fun onListPilihBusClick(v: View, data: BusResponse) {
    }
}