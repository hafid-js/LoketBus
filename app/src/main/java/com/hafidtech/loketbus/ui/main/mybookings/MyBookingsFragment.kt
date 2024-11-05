package com.hafidtech.loketbus.ui.main.mybookings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hafidtech.loketbus.databinding.FragmentMybookingsBinding

class MyBookingsFragment : Fragment() {

    private var _binding: FragmentMybookingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val myBookingsViewModel =
            ViewModelProvider(this).get(MyBookingsViewModel::class.java)

        _binding = FragmentMybookingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textMybookings
        myBookingsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}