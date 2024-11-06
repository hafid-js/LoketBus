package com.hafidtech.loketbus.ui.dialog.bottomsheet

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
import com.hafidtech.loketbus.R
import com.hafidtech.loketbus.databinding.BottomSheetListTerminalBinding
import com.hafidtech.loketbus.ui.dialog.bottomsheet.adapter.ListBottomPenumpangAdapter
import com.hafidtech.loketbus.ui.dialog.bottomsheet.adapter.ListBottomTerminalAdapter
import com.hafidtech.loketbus.ui.dialog.bottomsheet.adapter.ListBottomTipeBusAdapter
import com.hafidtech.loketbus.ui.model.TerminalModel
import okhttp3.internal.http2.Http2Connection.Listener

class ListTipeBusBottomSheet : BaseBindingBottomSheet(), ListBottomTipeBusAdapter.ItemAdapterCallback {

    private lateinit var binding : BottomSheetListTerminalBinding
    private var dialogPosition : Int = 0
    private var listener : Listener ?= null
    private lateinit var data : ArrayList<String>
    private lateinit var title : String
    private lateinit var subtitle : String

    override fun getFragmentView(): ViewBinding {
        binding = BottomSheetListTerminalBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        binding.tvTitle.text = title
        binding.tvSubtitle.text = subtitle

        var adapter = ListBottomTipeBusAdapter(data, this)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.rvListString.layoutManager = layoutManager
        binding.rvListString.adapter = adapter
        binding.rvListString.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    override fun onListBottomTerminalClick(v: View, data: String) {
        dialog?.dismiss()
        listener?.onClick(data)
    }

    interface Listener {
        fun onClick(data: String)
    }

    companion object {
        fun newInstance(listener:Listener, position : Int, data : ArrayList<String>,
                        title:String, subtitle:String) : ListTipeBusBottomSheet {

            val instance = ListTipeBusBottomSheet()
            instance.listener = listener
            instance.dialogPosition = position
            instance.data = data
            instance.title = title
            instance.subtitle = subtitle
            return instance

        }
    }


}

