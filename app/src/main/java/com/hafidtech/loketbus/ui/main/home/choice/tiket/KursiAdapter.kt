package com.hafidtech.loketbus.ui.main.home.choice.tiket

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hafidtech.loketbus.R
import com.hafidtech.loketbus.databinding.FragmentPilihKursiBinding
import com.hafidtech.loketbus.ui.model.response.KursiResponse
import com.hafidtech.loketbus.ui.utils.Const

class KursiAdapter(

    private val itemAdapterCallback : ItemKursiAdapterCallback
) : RecyclerView.Adapter <KursiAdapter.ViewHolder>() {

    private var listData = ArrayList<KursiResponse>()
    fun setData (listDataParms: ArrayList<KursiResponse>) {
        this.listData = listDataParms
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): KursiAdapter.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        return if (viewType == Const.RECYLERVIEW_ITEM_KURSI.VIEW_TYPE_ITEM) {
            val view = layoutInflater.inflate(R.layout.item_kursi, parent, false)
            ViewHolder(view)
        } else {
            val view = layoutInflater.inflate(R.layout.item_kursi_label, parent, false)
            ViewHolder(view)
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder.itemViewType == Const.RECYLERVIEW_ITEM_KURSI.VIEW_TYPE_ITEM) {
            holder.bind(listData[position], itemAdapterCallback, position)
        } else {
            holder.bindLabel(listData[position])
        }

    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position%5==0) {
            return Const.RECYLERVIEW_ITEM_KURSI.VIEW_TYPE_ITEM_LABEL
        } else {
            return Const.RECYLERVIEW_ITEM_KURSI.VIEW_TYPE_ITEM
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: KursiResponse, itemAdapterCallback: ItemKursiAdapterCallback, position: Int) {
            val iv_kursi: ImageView = itemView.findViewById(R.id.iv_kursi)
            itemView.apply {
                if (data.statusKursi == true) {
                    iv_kursi.setImageResource(R.drawable.ic_kursi_filled)
                } else {
                    iv_kursi.setOnClickListener {
                        if (data.checkKursi == true) {
                            iv_kursi.setImageResource(R.drawable.ic_kursi_empty)
                            itemAdapterCallback.onitemKursiAdapterCallback(
                                it, data, false, position
                            )
                        } else {
                            iv_kursi.setImageResource(R.drawable.ic_kursi_check)
                            itemAdapterCallback.onitemKursiAdapterCallback(
                                it, data, true, position
                            )
                        }
                    }
                }
            }

        }

        fun bindLabel(data:KursiResponse) {
            val tv_kursi: TextView = itemView.findViewById(R.id.tv_kursi)
            itemView.apply {
                tv_kursi.text = data.nameKursi
            }
        }
    }

    interface ItemKursiAdapterCallback {
        fun onitemKursiAdapterCallback(v:View, data:KursiResponse, check:Boolean, position: Int)
    }
}