package com.hafidtech.loketbus.ui.auth

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingActivity
import com.hafidtech.loketbus.databinding.ActivityAuthBinding

class AuthActivity : BaseBindingActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun getActivityBinding(): ViewBinding {
        binding = ActivityAuthBinding.inflate(layoutInflater)
        return binding
    }

    override fun onBindView() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}