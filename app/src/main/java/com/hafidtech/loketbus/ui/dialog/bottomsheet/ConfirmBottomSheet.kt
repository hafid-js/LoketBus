package com.hafidtech.loketbus.ui.dialog.bottomsheet

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingBottomSheet
import com.bagicode.bagicodebaseutils.utils.loadRoundedImage
import com.hafidtech.loketbus.R
import com.hafidtech.loketbus.databinding.BottomSheetConfirmBinding
import com.hafidtech.loketbus.databinding.BottomSheetListTerminalBinding
import com.hafidtech.loketbus.ui.dialog.bottomsheet.adapter.ListBottomPenumpangAdapter
import com.hafidtech.loketbus.ui.dialog.bottomsheet.adapter.ListBottomTerminalAdapter
import com.hafidtech.loketbus.ui.dialog.bottomsheet.adapter.ListBottomTipeBusAdapter
import com.hafidtech.loketbus.ui.model.TerminalModel
import com.hafidtech.loketbus.ui.model.response.BusResponse
import com.hafidtech.loketbus.ui.model.response.KursiResponse
import okhttp3.internal.http2.Http2Connection.Listener

class ConfirmBottomSheet : BaseBindingBottomSheet(){

    private var listener : Listener ?= null
    private var dialogPosition : Int = 0

    private lateinit var dataKursi : ArrayList<KursiResponse>
    private lateinit var dataBus : BusResponse
    private lateinit var binding : BottomSheetConfirmBinding

    override fun getFragmentView(): ViewBinding {
        binding = BottomSheetConfirmBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        binding.tvTime.text = dataBus.jam
        binding.tvTipe.text = dataBus.classBus
        binding.ivLogo.loadRoundedImage(dataBus.logo, 4)

        var showLabelKursi = ""
        for (i in dataKursi.indices) {
            showLabelKursi += "${dataKursi.get(i).nameKursi}"
        }

        binding.tvSeat.text = showLabelKursi.substring(0, showLabelKursi.length-1)

        binding.btnLanjutkan.setOnClickListener{
            listener?.onOptionClick(dialog!!, dialogPosition)
        }
    }

    interface Listener {
        fun onOptionClick(dialog: Dialog, position: Int)
    }

    companion object {
        fun newInstance(listener: Listener, dataBusResponse: BusResponse, dataKursiParms: ArrayList<KursiResponse>): ConfirmBottomSheet {
            val instance = ConfirmBottomSheet()
            instance.listener = listener
            instance.dataBus = dataBusResponse
            instance.dataKursi = dataKursiParms
            return instance
        }
    }


}

