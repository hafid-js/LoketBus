package com.hafidtech.loketbus.ui.main

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingActivity
import com.hafidtech.loketbus.R
import com.hafidtech.loketbus.databinding.ActivityMainBinding

class MainActivity : BaseBindingActivity() {


    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun getActivityBinding(): ViewBinding {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {

        navController = findNavController(R.id.nav_host_fragment)

        binding.navView.setupWithNavController(navController)
        binding.navView.setOnNavigationItemSelectedListener {
            if (it.itemId != binding.navView.selectedItemId)
                NavigationUI.onNavDestinationSelected(it, navController)
            true
        }

    }
}