package com.hafidtech.loketbus.ui.main.home.choice.bus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hafidtech.loketbus.databinding.ItemBusBinding
import com.hafidtech.loketbus.ui.model.response.BusResponse

class PilihBusAdapter(

    private val listData : ArrayList<BusResponse>,
    private val itemAdapterCallback : ItemAdapterCallback
) : RecyclerView.Adapter <PilihBusAdapter.ViewHolder>() {

    lateinit var binding : ItemBusBinding

    interface ItemAdapterCallback {
        fun onListPilihBusClick(v : View, data:BusResponse)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PilihBusAdapter.ViewHolder {
        binding = ItemBusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ViewHolder(binding : ItemBusBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BusResponse, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                binding.tvItemTitle.text = data.title
                binding.tvItemSub.text = data.classBus

                itemView.setOnClickListener {
                    itemAdapterCallback.onListPilihBusClick(it, data)
                }

                var timeArray = arrayListOf<String>("07.00", "12.00", "17.30", "20.40")
                var timeAdapter = TimeAdapter(timeArray, data.jam!!)
                val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.rvTimeBus.layoutManager = layoutManager
                binding.rvTimeBus.adapter = timeAdapter



            }
        }
    }
}