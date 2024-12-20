package com.hafidtech.loketbus.ui.main.home

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingActivity
import com.hafidtech.loketbus.R
import com.hafidtech.loketbus.databinding.ActivityHomeDetailBinding


class HomeDetailActivity : BaseBindingActivity() {

    private lateinit var binding : ActivityHomeDetailBinding

    override fun getActivityBinding(): ViewBinding {
       binding = ActivityHomeDetailBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        intent.extras?.let {
            val navController = Navigation.findNavController(findViewById(R.id.rv_bus))
            val bundle = Bundle()
            bundle.putParcelable("data", it.get("data") as Parcelable?)
            navController.setGraph(navController.graph, bundle)
            }
    }
}