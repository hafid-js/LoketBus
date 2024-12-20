package com.hafidtech.loketbus.ui.dialog.bottomsheet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hafidtech.loketbus.databinding.ItemTerminalBinding
import com.hafidtech.loketbus.ui.model.TerminalModel

class ListBottomTerminalAdapter(

    private val listData : ArrayList<TerminalModel>,
            private val itemAdapterCallback : ItemAdapterCallback
) : RecyclerView.Adapter<ListBottomTerminalAdapter.ViewHolder>() {

    lateinit var binding : ItemTerminalBinding
    interface ItemAdapterCallback {
        fun onListBottomTerminalClick(v : View, data: TerminalModel)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListBottomTerminalAdapter.ViewHolder {
        binding = ItemTerminalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ViewHolder(binding : ItemTerminalBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: TerminalModel, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                binding.tvItemTitle.text = data.namaTerminal
                binding.tvItemSub.text = data.codeTerminal

                itemView.setOnClickListener {
                    itemAdapterCallback.onListBottomTerminalClick(it, data)
                }
            }

        }
    }
}