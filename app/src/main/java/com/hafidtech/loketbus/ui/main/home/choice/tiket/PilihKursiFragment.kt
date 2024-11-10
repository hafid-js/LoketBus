package com.hafidtech.loketbus.ui.main.home.choice.tiket

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingFragment
import com.bagicode.bagicodebaseutils.utils.Const
import com.bagicode.bagicodebaseutils.utils.loadRoundedImage
import com.bagicode.bagicodebaseutils.utils.visible
import com.hafidtech.loketbus.R
import com.hafidtech.loketbus.databinding.FragmentPilihKursiBinding
import com.hafidtech.loketbus.ui.dialog.bottomsheet.ConfirmBottomSheet
import com.hafidtech.loketbus.ui.model.BusRequest
import com.hafidtech.loketbus.ui.model.response.BusResponse
import com.hafidtech.loketbus.ui.model.response.KursiResponse

class PilihKursiFragment : BaseBindingFragment(), KursiAdapter.ItemKursiAdapterCallback, PilihKursiContract.View {

    lateinit var binding : FragmentPilihKursiBinding
    lateinit var presenter: PilihKursiPresenter
    lateinit var adapter: KursiAdapter

    var dummyListTemp = ArrayList<KursiResponse>()
    var listKursi = ArrayList<KursiResponse>()

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

        binding.ivBus.loadRoundedImage(busParms?.image, 4)
        binding.btnLanjutkan.setOnClickListener{
            if (dataPick?.penumpang == listKursi.size) {
                ConfirmBottomSheet.newInstance(object : ConfirmBottomSheet.Listener {
                    override fun onOptionClick(dialog: Dialog, position: Int) {
//                        dialog.dismiss()
                        var bundle = Bundle()
                        bundle.putParcelable("data", busParms)
                        bundle.putParcelable("dataPick", dataPick)
                        bundle.putParcelableArrayList("dataKursi", listKursi)
                        findNavController().navigate(R.id.action_personal_info, bundle)
                    }
                }, busParms!!, listKursi).show(parentFragmentManager, "")
            } else {
                showSnackbarMessage(binding.btnLanjutkan, "Total kursi yang kamu pesan ${dataPick?.penumpang} slot",
                    Const.ToastType.Error)
            }
        }

    }

    override fun onitemKursiAdapterCallback(
        v: View,
        data: KursiResponse,
        check: Boolean,
        position: Int
    ) {
        var selectKursi = dummyListTemp.get(position).apply {
            checkKursi = check
        }
        adapter.setData(dummyListTemp)

        if (check) {
            listKursi.add(selectKursi)
        } else {
            listKursi.remove(selectKursi)
        }

        if (listKursi.size == dataPick?.penumpang) {
            binding.btnLanjutkan.visible()
        }
    }

    override fun onKursiSuccess(response: ArrayList<KursiResponse>) {

        dummyListTemp = response
        adapter = KursiAdapter(this)
        adapter.setData(response)

        val layoutManager = GridLayoutManager(context, 5)
        binding.rvKursi.layoutManager = layoutManager
        binding.rvKursi.adapter = adapter
    }

    override fun onKursiFailed(message: String) {
        showSnackbarMessage(binding.rvKursi, message, Const.ToastType.Error) }
}