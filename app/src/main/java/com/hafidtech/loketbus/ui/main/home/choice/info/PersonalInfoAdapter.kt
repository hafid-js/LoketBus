package com.hafidtech.loketbus.ui.main.home.choice.info

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hafidtech.loketbus.R
import com.hafidtech.loketbus.databinding.ItemPenumpangBinding

class PersonalInfoAdapter(

    private val listData: ArrayList<String>,

    private val itemAdapterCallback : ItemPenumpangAdapterCallback
) : RecyclerView.Adapter <PersonalInfoAdapter.ViewHolder>() {
    lateinit var binding : ItemPenumpangBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PersonalInfoAdapter.ViewHolder {
        binding = ItemPenumpangBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder : ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback, position)
    }

    override fun getItemCount(): Int {
       return listData.size
    }


    inner class ViewHolder(binding: ItemPenumpangBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String, itemAdapterCallback: ItemPenumpangAdapterCallback, position: Int) {
            itemView.apply {
                binding.tvName.text = data
                binding.ivName.setOnClickListener{
                    itemAdapterCallback.onitemPenumpangAdapterCallback(data, position)
                }

            }
        }
}


    interface ItemPenumpangAdapterCallback {
        fun onitemPenumpangAdapterCallback(data: String, position: Int)
    }
}