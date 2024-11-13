package com.hafidtech.loketbus.ui.main.home.choice.info

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingFragment
import com.bagicode.bagicodebaseutils.utils.Const
import com.bagicode.bagicodebaseutils.utils.changePage
import com.bagicode.bagicodebaseutils.utils.formatPrice
import com.bagicode.bagicodebaseutils.utils.loadRoundedImage
import com.google.gson.Gson
import com.hafidtech.loketbus.R
import com.hafidtech.loketbus.databinding.FragmentPersonalInfoBinding
import com.hafidtech.loketbus.ui.HafidTechLoketBus
import com.hafidtech.loketbus.ui.auth.AuthActivity
import com.hafidtech.loketbus.ui.dialog.bottomsheet.InputEmailBottomSheet
import com.hafidtech.loketbus.ui.main.MainActivity
import com.hafidtech.loketbus.ui.model.BusRequest
import com.hafidtech.loketbus.ui.model.request.CheckoutRequest
import com.hafidtech.loketbus.ui.model.request.Penumpang
import com.hafidtech.loketbus.ui.model.response.BusResponse
import com.hafidtech.loketbus.ui.model.response.KursiResponse
import com.hafidtech.loketbus.ui.model.response.LoginResponse
import com.hafidtech.loketbus.ui.network.HttpClient

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PersonalInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PersonalInfoFragment : BaseBindingFragment(), PersonalInfoAdapter.ItemPenumpangAdapterCallback, PersonalInfoContract.View {

    private lateinit var binding : FragmentPersonalInfoBinding

    private var busParms : BusResponse?=null
    private var dataPick : BusRequest?=null
    private var dataKursi : ArrayList<KursiResponse>?=null

    private var dataPassenger = ArrayList<String>()
    private var emailContactParms : String = ""
    lateinit var totalParms : Int = 0

    lateinit var adapterPassenger : PersonalInfoAdapter
    lateinit var presenter: PersonalInfoPresenter
    lateinit var userResponse: LoginResponse


    override fun getFragmentView(): ViewBinding {
        binding = FragmentPersonalInfoBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
       busParms = arguments?.getParcelable("data")
        dataPick = arguments?.getParcelable("dataPick")
        dataKursi = arguments?.getParcelableArrayList("dataKursi")

        presenter = PersonalInfoPresenter(this)

        initView()
        initListener()
    }

    private fun initView() {
        binding.ivLogo.loadRoundedImage(busParms?.logo, 4)
        totalParms = dataPick?.penumpang!! * busParms?.price?.toInt()!!
        binding.tvPrice.formatPrice(totalParms.toString())

        binding.tvTime.text = "${busParms?.jam} WIB"

        var user = HafidTechLoketBus.getApp().getUser()
        userResponse = Gson().fromJson(user, LoginResponse::class.java)
        binding.tvEmail.text = userResponse.email
        dataPassenger.add(userResponse?.username!!)
        adapterPassenger = PersonalInfoAdapter(dataPassenger, this)
        val layoutManager = LinearLayoutManager(activity)
        binding.rvPassenger.layoutManager = layoutManager
        binding.rvPassenger.adapter = adapterPassenger
    }

    private fun initListener() {

        binding.tvAddPassenger.setOnClickListener{
            if (dataPassenger.size != dataPick?.penumpang) {
                InputEmailBottomSheet.newInstance(object : InputEmailBottomSheet.Listener {
                    override fun onClick(data: String) {
                       dataPassenger.add(data)
                        adapterPassenger.notifyDataSetChanged()
                    }
                }, "Penumpang", "Silahkan isi nama lengkap").show(parentFragmentManager, "")
            } else {
                showSnackbarMessage(binding.btnLanjutkan, "Total kursi yang kamu pesan ${dataPick?.penumpang} Slot", Const.ToastType.Error)
            }
        }


        binding.ivEmail.setOnClickListener{
            var emailParms = binding.tvEmail.text.toString()

            InputEmailBottomSheet.newInstance(object : InputEmailBottomSheet.Listener {
                override fun onClick(data: String) {
                    emailContactParms = data
                    binding.tvEmail.text = data
                }

            }, "Email", "Silahkan masukkan email salah penumpang", emailParms).show(parentFragmentManager)
        }
        binding.btnLanjutkan.setOnClickListener{
            if (dataPassenger.size == dataPick?.penumpang) {
                setCheckout(
                    dataPassenger,
                    dataKursi!!,
                    busParms!!,
                    dataPick!!,
                    it,
                    "progress",
                    "transfer"
                )
            } else {
                showSnackbarMessage(binding.btnLanjutkan, "Total kursi yang kamu pesan ${dataPick?.penumpang} Slot", Const.ToastType.Error)
            }
        }
    }

    override fun onitemPenumpangAdapterCallback(data: String, position: Int) {
        InputEmailBottomSheet.newInstance(object : InputEmailBottomSheet.Listener {
            override fun onClick(data: String) {
                dataPassenger.set(position, data)
                adapterPassenger.notifyDataSetChanged()
            }

        }, "Penumpang", "Silahkan masukkan nama lengkap", data).show(parentFragmentManager)
    }

    override fun onCheckoutBookingSuccess(id: String, view: View) {
        Navigation.findNavController(view).navigate(R.id.action_success, null)
    }

    override fun onCheckoutUpdateSuccess(message: String, view: View) {

    }

    override fun onCheckoutUpdateFailed(message: String) {
        showSnackbarMessage(binding.btnLanjutkan, message, Const.ToastType.Error)

    }

    override fun onCheckoutFailed(message: String) {
        showSnackbarMessage(binding.btnLanjutkan, message, Const.ToastType.Error)
    }

    private fun setCheckout(dataPassengerParms : ArrayList<String>,
                            dataKursiParms : ArrayList<KursiResponse>,
                            busParms : BusResponse,
                            dataPickParms : BusRequest,
                            view : View,
                            statusBayar : String,
                            jenisBayar : String) {

        var penumpangTemp = ArrayList<Penumpang>()
        var checkoutRequest : CheckoutRequest

        for (i in dataPassengerParms.indices) {
            penumpangTemp.add(
                Penumpang(
                    "",
                    dataKursiParms.get(i).nameKursi,
                    dataPassengerParms.get(i))
            )
        }

        checkoutRequest = CheckoutRequest(
            busParms.logo,
            busParms.title,
            busParms.plat,
            dataPickParms.dariCode,
            dataPickParms.tujuanCode,
            dataPickParms.date,
            emailContactParms,
            "",
            userResponse.idUser,
            busParms.jam,
            jenisBayar,
            penumpangTemp,
            statusBayar,
            dataPickParms.dari,
            dataPickParms.tujuan,
            busParms.classBus,
            totalParms.toString()

        )

        presenter.setCheckoutBooking(checkoutRequest, view)

    }
}