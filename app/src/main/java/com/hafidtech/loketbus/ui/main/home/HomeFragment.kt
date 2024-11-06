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
import com.bagicode.bagicodebaseutils.utils.Const
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.hafidtech.loketbus.R
import com.hafidtech.loketbus.databinding.FragmentHomeBinding
import com.hafidtech.loketbus.ui.dialog.bottomsheet.ListPenumpangBottomSheet
import com.hafidtech.loketbus.ui.dialog.bottomsheet.ListTerminalBottomSheet
import com.hafidtech.loketbus.ui.dialog.bottomsheet.ListTipeBusBottomSheet
import com.hafidtech.loketbus.ui.model.TerminalModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.TimeZone

class HomeFragment : BaseBindingFragment() {

    private lateinit var binding : FragmentHomeBinding
    private var dataDari = ArrayList<TerminalModel>()
    private var dataTujuan = ArrayList<TerminalModel>()
    private var dataPenumpang = ArrayList<Int>()
    private var dataTipeBus = ArrayList<String>()

    override fun getFragmentView(): ViewBinding {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        initData()
        initListener()

    }

    private fun initListener() {
        binding.ivDari.setOnClickListener {

            ListTerminalBottomSheet.newInstance(
                object : ListTerminalBottomSheet.Listener {
                    override fun onClick(data: TerminalModel) {
                        binding.tvDariValue.text = data.namaTerminal
                    }
                },
                0, dataDari,
                "Pilih Terminal Keberangkatan",
                "Silahkan sesuaikan terminal keberangkatan Anda"
            ).show(parentFragmentManager, "")
        }

        binding.ivTujuan.setOnClickListener {

            ListTerminalBottomSheet.newInstance(
                object : ListTerminalBottomSheet.Listener {
                    override fun onClick(data: TerminalModel) {
                        binding.tvTujuanValue.text = data.namaTerminal
                    }
                },
                0, dataTujuan,
                "Pilih Terminal Tujuan",
                "Silahkan sesuaikan terminal tujuan Anda"
            ).show(parentFragmentManager, "")
        }

        binding.ivPenumpang.setOnClickListener {

            ListPenumpangBottomSheet.newInstance(
                object : ListPenumpangBottomSheet.Listener {
                    override fun onClick(data: Int) {
                        binding.tvPenumpangValue.text = "${data} Penumpang"
                    }
                },
                0, dataPenumpang,
                "Penumpang",
                "Silahkan sesuaikan jumlah penumpang"
            ).show(parentFragmentManager, "")
        }

        binding.ivTipeBus.setOnClickListener {

            ListTipeBusBottomSheet.newInstance(
                object : ListTipeBusBottomSheet.Listener {
                    override fun onClick(data: String) {
                        binding.tvTipeBusValue.text = data
                    }
                },
                0, dataTipeBus,
                "Tipe Bus",
                "Silahkan pilih tipe bus"
            ).show(parentFragmentManager, "")
        }

        binding.ivDate.setOnClickListener{
            var calendarSetting = CalendarConstraints.Builder()
            calendarSetting.setValidator(DateValidatorPointForward.now())

            var materialDatePickerBuilder : MaterialDatePicker.Builder<*> = MaterialDatePicker.Builder.datePicker()
            materialDatePickerBuilder.setTitleText("Tanggal Keberangkatan")
            materialDatePickerBuilder.setCalendarConstraints(calendarSetting.build())

            var materialDatePicker = materialDatePickerBuilder.build()
            materialDatePicker.show(parentFragmentManager, "DATE_PICKER")

            materialDatePicker.addOnPositiveButtonClickListener {
                val utc = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                utc.timeInMillis = it as Long

                var format = SimpleDateFormat(Const.DATE_FORMAT_DAY_DATE_MONTH_YEAR)
                val formatted = format.format(utc.time)

                binding.tvDateValue.text = formatted
            }
        }
    }

    private fun initData() {
        dataDari.add(TerminalModel("Terminal Pulo Gebang", "PGB"))
        dataDari.add(TerminalModel("Terminal Kutoarjo", "KTJ"))

        dataTujuan.add(TerminalModel("Terminal Kebumen", "KBM"))
        dataTujuan.add(TerminalModel("Terminal Arjosari", "ARJ"))

        dataPenumpang.add(1)
        dataPenumpang.add(2)

        dataTipeBus.add("Semua")
        dataTipeBus.add("Bisnis")
        dataTipeBus.add("Ekonomi")
    }
}