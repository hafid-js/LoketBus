package com.hafidtech.loketbus.ui.splash


import android.os.Handler
import android.widget.ImageView
import androidx.viewbinding.ViewBinding
import com.bagicode.bagicodebaseutils.base.BaseActivity
import com.bagicode.bagicodebaseutils.basewithbinding.BaseBindingActivity
import com.bagicode.bagicodebaseutils.utils.changePage
import com.hafidtech.loketbus.R
import com.hafidtech.loketbus.databinding.ActivitySplashscreenBinding
import com.hafidtech.loketbus.ui.auth.AuthActivity


class SplashscreenActivity : BaseBindingActivity() {

    private lateinit var binding : ActivitySplashscreenBinding

    override fun getActivityBinding(): ViewBinding {
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        return binding
    }


    override fun onBindView() {

        Handler().postDelayed({
            changePage(AuthActivity::class.java, null, true)
        }, 3000)
    }

}