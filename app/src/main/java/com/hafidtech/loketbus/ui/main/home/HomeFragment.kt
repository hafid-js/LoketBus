package com.hafidtech.loketbus.ui.main.home

import android.content.Intent
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
import com.bagicode.bagicodebaseutils.utils.Helpers.getCurrentDate
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.hafidtech.loketbus.R
import com.hafidtech.loketbus.databinding.FragmentHomeBinding
import com.hafidtech.loketbus.ui.dialog.bottomsheet.ListPenumpangBottomSheet
import com.hafidtech.loketbus.ui.dialog.bottomsheet.ListTerminalBottomSheet
import com.hafidtech.loketbus.ui.dialog.bottomsheet.ListTipeBusBottomSheet
import com.hafidtech.loketbus.ui.main.home.choice.bus.PilihBusAdapter
import com.hafidtech.loketbus.ui.model.BusRequest
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
    private lateinit var busRequest: BusRequest

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

                        busRequest.apply {
                            dari = data.namaTerminal
                            dariCode = data.codeTerminal
                        }
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

                        busRequest.apply {
                            tujuan = data.namaTerminal
                            tujuanCode = data.codeTerminal
                        }
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

                        busRequest.apply {
                            penumpang = data
                        }
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

                        busRequest.apply {
                            tipe = data
                        }
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
                busRequest.apply {
                    date = formatted
                }
            }
        }

        binding.btnCari.setOnClickListener{

            if (busRequest.dari.isNullOrEmpty()) {
                showSnackbarMessage(binding.btnCari, "Silahkan pilih terminal keberangkatan", Const.ToastType.Error)
            } else if (busRequest.tujuan.isNullOrEmpty()) {
                showSnackbarMessage(binding.btnCari, "Silahkan pilih terminal tujuan", Const.ToastType.Error)
            } else if (busRequest.penumpang == 0) {
            showSnackbarMessage(binding.btnCari, "Silahkan tentukan jumlah penumpang", Const.ToastType.Error)
            }else if (busRequest.tipe.isNullOrEmpty()) {
                showSnackbarMessage(binding.btnCari, "Silahkan pilih tipe bus", Const.ToastType.Error)
            }else {
                startActivity(Intent(requireContext(), HomeDetailActivity::class.java)
                    .putExtra("data", busRequest))

            }
        }
    }

    private fun initData() {
        dataDari.add(TerminalModel("Terminal Pulo Gebang", "PGD"))
        dataDari.add(TerminalModel("Terminal Depok", "DPK"))

        dataTujuan.add(TerminalModel("Terminal Ampera", "APR"))
        dataTujuan.add(TerminalModel("Terminal Bengkulu", "BKL"))

        dataPenumpang.add(1)
        dataPenumpang.add(2)

        dataTipeBus.add("Semua")
        dataTipeBus.add("Bisnis")
        dataTipeBus.add("Ekonomi")


        busRequest = BusRequest()
        busRequest.apply {
            date = getCurrentDate()
        }
    }
}