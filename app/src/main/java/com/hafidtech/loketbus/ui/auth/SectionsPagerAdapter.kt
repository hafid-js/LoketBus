package com.hafidtech.loketbus.ui.auth

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.hafidtech.loketbus.R
import com.hafidtech.loketbus.ui.auth.signin.SigninFragment
import com.hafidtech.loketbus.ui.auth.signup.SignupFragment

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
)
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
       return when (position) {
           0 -> {
               return SigninFragment()
           }
           1 -> {
               return SignupFragment()
           }
           else -> {
               return SigninFragment()
           }
       }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}