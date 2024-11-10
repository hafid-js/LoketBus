package com.hafidtech.loketbus.ui.main.home.choice.bus

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorLong
import androidx.recyclerview.widget.RecyclerView
import com.hafidtech.loketbus.R
import com.hafidtech.loketbus.databinding.ItemBusBinding
import com.hafidtech.loketbus.databinding.ItemTerminalBinding
import com.hafidtech.loketbus.databinding.ItemTimeBinding
import com.hafidtech.loketbus.ui.model.BusRequest
import com.hafidtech.loketbus.ui.model.TerminalModel
import com.hafidtech.loketbus.ui.model.response.BusResponse

class TimeAdapter(

    private val listData : ArrayList<String>, private val dataParms : String
) : RecyclerView.Adapter<TimeAdapter.ViewHolder>() {

    lateinit var binding : ItemTimeBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TimeAdapter.ViewHolder {
        binding = ItemTimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ViewHolder(binding : ItemTimeBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: String) {
            itemView.apply {
                binding.tvItemString.text = data

                if (data.equals(dataParms)) {
                    binding.tvItemString.text = data
                    binding.tvItemString.setTextColor(resources.getColor(R.color.white))
                    binding.tvItemString.setBackgroundResource(R.drawable.sp_rectangle_radius_20_blue)
                }
            }

        }
    }
}