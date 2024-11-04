package com.hafidtech.loketbus.ui.auth

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingActivity
import com.bagicode.bagicodebaseutils.utils.changePage
import com.hafidtech.loketbus.databinding.ActivityAuthBinding
import com.hafidtech.loketbus.ui.HafidTechLoketBus
import com.hafidtech.loketbus.ui.main.MainActivity

class AuthActivity : BaseBindingActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun getActivityBinding(): ViewBinding {
        binding = ActivityAuthBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        if(!HafidTechLoketBus.getApp().getToken().isNullOrEmpty()) {
            changePage(MainActivity::class.java, null, true)
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)

    }
}