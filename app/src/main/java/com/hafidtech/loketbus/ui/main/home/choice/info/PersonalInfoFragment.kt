package com.hafidtech.loketbus.ui.main.home.choice.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingFragment
import com.bagicode.bagicodebaseutils.utils.formatPrice
import com.bagicode.bagicodebaseutils.utils.loadRoundedImage
import com.google.gson.Gson
import com.hafidtech.loketbus.R
import com.hafidtech.loketbus.databinding.FragmentPersonalInfoBinding
import com.hafidtech.loketbus.ui.HafidTechLoketBus
import com.hafidtech.loketbus.ui.model.BusRequest
import com.hafidtech.loketbus.ui.model.response.BusResponse
import com.hafidtech.loketbus.ui.model.response.KursiResponse
import com.hafidtech.loketbus.ui.model.response.LoginResponse

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PersonalInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PersonalInfoFragment : BaseBindingFragment(), PersonalInfoAdapter.ItemPenumpangAdapterCallback {

    private lateinit var binding : FragmentPersonalInfoBinding

    private var busParms : BusResponse?=null
    private var dataPick : BusRequest?=null
    private var dataKursi : ArrayList<KursiResponse>?=null

    private var dataPassenger = ArrayList<String>()

    override fun getFragmentView(): ViewBinding {
        binding = FragmentPersonalInfoBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
       busParms = arguments?.getParcelable("data")
        dataPick = arguments?.getParcelable("dataPick")
        dataKursi = arguments?.getParcelableArrayList("dataKursi")

        initView()
        initListener()
    }

    private fun initListener() {
        binding.ivLogo.loadRoundedImage(busParms?.logo, 4)
        var totalParms = dataPick?.penumpang!! * busParms?.price?.toInt()!!
        binding.tvPrice.formatPrice(totalParms.toString())

        binding.tvTime.text = "${busParms?.jam} WIB"

        var user = HafidTechLoketBus.getApp().getUser()
        var userResponse = Gson().fromJson(user, LoginResponse::class.java)
        binding.tvEmail.text = userResponse.email

        dataPassenger.add(userResponse?.username!!)
        var adapterPassenger = PersonalInfoAdapter(dataPassenger, this)
        val layoutManager = LinearLayoutManager(activity)
        binding.rvPassenger.layoutManager = layoutManager
        binding.rvPassenger.adapter = adapterPassenger
    }

    private fun initView() {

    }

    override fun onitemPenumpangAdapterCallback(data: String, position: Int) {

    }
}