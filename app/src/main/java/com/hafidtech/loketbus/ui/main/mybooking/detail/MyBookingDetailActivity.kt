package com.hafidtech.loketbus.ui.main.mybooking.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingActivity
import com.bagicode.bagicodebaseutils.utils.loadRoundedImage
import com.hafidtech.loketbus.R
import com.hafidtech.loketbus.databinding.ActivityMyBookingDetailBinding
import com.hafidtech.loketbus.ui.model.response.MyBookingResponse

class MyBookingDetailActivity : BaseBindingActivity() {

    lateinit var binding : ActivityMyBookingDetailBinding

    override fun getActivityBinding(): ViewBinding {
        binding = ActivityMyBookingDetailBinding.inflate(layoutInflater)
        return binding

    }

    override fun onBindView() {
        intent.extras?.let {
            var dataIntent = it.getParcelable<MyBookingResponse>("data")
            initView(dataIntent)
        }
    }

    private fun initView(data: MyBookingResponse?) {
        binding.tvCodeFrom.text = data?.codeTerminalDari
        binding.tvCodeTo.text = data?.codeTerminalTujuan
        binding.tvLocationFrom.text = data?.terminalDari
        binding.tvLocationTo.text = data?.terminalTujuan

        var showName=""
        var showKursi=""

        for (i in data?.penumpang!!.indices) {
            showName = "${data?.penumpang?.get(i)?.nama}"
            showKursi = "${data?.penumpang?.get(i)?.kursi}"
        }

        binding.tvName.text = showName.substring(0, showName.length-1)
        binding.tvKursi.text = showKursi.substring(0, showKursi.length-1)

        binding.tvDate.text = data?.dateKeberangkatan
        binding.tvTime.text = data?.jamKeberangkatan

        binding.ivLogo.loadRoundedImage(data?.busLogo, 4)
        binding.tvTrans.text = data?.pembayaran

        binding.tvPlat.text = data?.busPlat
        binding.tvBooking.text = data?.idTiket
    }
}