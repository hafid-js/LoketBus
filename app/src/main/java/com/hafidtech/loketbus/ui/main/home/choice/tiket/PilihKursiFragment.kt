package com.hafidtech.loketbus.ui.main.home.choice.tiket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingFragment
import com.bagicode.bagicodebaseutils.utils.Const
import com.hafidtech.loketbus.R
import com.hafidtech.loketbus.databinding.FragmentPilihKursiBinding
import com.hafidtech.loketbus.ui.model.BusRequest
import com.hafidtech.loketbus.ui.model.response.BusResponse
import com.hafidtech.loketbus.ui.model.response.KursiResponse

class PilihKursiFragment : BaseBindingFragment(), KursiAdapter.ItemKursiAdapterCallback, PilihKursiContract.View {

    lateinit var binding : FragmentPilihKursiBinding
    lateinit var presenter: PilihKursiPresenter
    lateinit var adapter: KursiAdapter

    private var busParms : BusResponse ?=null
    private var dataPick : BusRequest ?=null

    override fun getFragmentView(): ViewBinding {
        binding = FragmentPilihKursiBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        presenter = PilihKursiPresenter(this)

        busParms = arguments?.getParcelable("data")
        dataPick = arguments?.getParcelable("dataPick")

        busParms?.code?.let {
            presenter.getKursiList(it)
        }

    }

    override fun onitemKursiAdapterCallback(
        v: View,
        data: KursiResponse,
        check: Boolean,
        position: Int
    ) {

    }

    override fun onKursiSuccess(response: ArrayList<KursiResponse>) {
        adapter = KursiAdapter(this)
        adapter.setData(response)

        val layoutManager = GridLayoutManager(context, 5)
        binding.rvKursi.layoutManager = layoutManager
        binding.rvKursi.adapter = adapter
    }

    override fun onKursiFailed(message: String) {
        showSnackbarMessage(binding.rvKursi, message, Const.ToastType.Error) }
}