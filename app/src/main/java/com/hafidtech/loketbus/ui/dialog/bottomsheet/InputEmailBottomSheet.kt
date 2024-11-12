package com.hafidtech.loketbus.ui.dialog.bottomsheet

import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingBottomSheet
import com.hafidtech.loketbus.databinding.BottomSheetInputEmailBinding
import com.hafidtech.loketbus.databinding.BottomSheetListTerminalBinding
import com.hafidtech.loketbus.ui.dialog.bottomsheet.adapter.ListBottomPenumpangAdapter

class InputEmailBottomSheet : BaseBindingBottomSheet(){

    private lateinit var binding: BottomSheetInputEmailBinding
    private var dialogPosition: Int = 0
    private var listener: Listener? = null
    private lateinit var title: String
    private lateinit var subtitle: String
    private var valueParms : String?=""

    override fun getFragmentView(): ViewBinding {
        binding = BottomSheetInputEmailBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        binding.tvTitle.text = title
        binding.tvSubtitle.text = subtitle

        if (!valueParms.isNullOrEmpty()) {
            binding.etInputEmail.setText(valueParms)
        }

        binding.btnSimpan.setOnClickListener {
            var valueString = binding.etInputEmail.text.toString()
            if (valueString.isNullOrEmpty()) {
                binding.etInputEmail.error = "Silahkan isi nama email"
            } else {
                dialog!!.dismiss()
                listener?.onClick(valueString)
            }
        }

    }

    interface Listener {
        fun onClick(data: String)
    }

    companion object {
       fun newInstance(listener: Listener, title: String, subtitle: String, valueParms: String?=""): InputEmailBottomSheet {

            val instance = InputEmailBottomSheet()
            instance.listener = listener
            instance.valueParms = valueParms
            instance.title = title
            instance.subtitle = subtitle
            return instance

        }
    }
}

