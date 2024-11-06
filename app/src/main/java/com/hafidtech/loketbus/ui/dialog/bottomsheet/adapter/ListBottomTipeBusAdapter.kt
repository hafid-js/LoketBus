package com.hafidtech.loketbus.ui.dialog.bottomsheet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hafidtech.loketbus.databinding.ItemStringBinding
import com.hafidtech.loketbus.databinding.ItemTerminalBinding
import com.hafidtech.loketbus.ui.model.TerminalModel

class ListBottomTipeBusAdapter(

    private val listData : ArrayList<String>,
    private val itemAdapterCallback : ItemAdapterCallback
) : RecyclerView.Adapter <ListBottomTipeBusAdapter.ViewHolder>() {

    lateinit var binding : ItemStringBinding

    interface ItemAdapterCallback {
        fun onListBottomTerminalClick(v : View, data:String)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListBottomTipeBusAdapter.ViewHolder {
        binding = ItemStringBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ViewHolder(binding : ItemStringBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                binding.tvItemString.text = data.toString()

                itemView.setOnClickListener {
                    itemAdapterCallback.onListBottomTerminalClick(it, data)
                }
            }
        }
    }
}