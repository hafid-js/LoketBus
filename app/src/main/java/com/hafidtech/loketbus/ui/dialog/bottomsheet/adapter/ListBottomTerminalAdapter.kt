package com.hafidtech.loketbus.ui.dialog.bottomsheet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hafidtech.loketbus.databinding.ItemStringBinding
import com.hafidtech.loketbus.ui.model.TerminalModel

class ListBottomTerminalAdapter(

    private val listData : ArrayList<TerminalModel>,
            private val itemAdapterCallback : ItemAdapterCallback
) : RecyclerView.Adapter<ListBottomTerminalAdapter.ViewHolder>() {

    lateinit var binding : ItemStringBinding
    interface ItemAdapterCallback {
        fun onListBottomTerminalClick(v : View, data: TerminalModel)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListBottomTerminalAdapter.ViewHolder {
        binding = ItemStringBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ViewHolder(binding : ItemStringBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: TerminalModel, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                binding.tvItemString.text = data.namaTerminal

                itemView.setOnClickListener {
                    itemAdapterCallback.onListBottomTerminalClick(it, data)
                }
            }

        }
    }
}