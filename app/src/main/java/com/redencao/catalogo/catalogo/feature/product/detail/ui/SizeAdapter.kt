package com.redencao.catalogo.catalogo.feature.product.detail.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.redencao.catalogo.catalogo.R
import com.redencao.catalogo.catalogo.util.extensions.inflate
import kotlinx.android.synthetic.main.item_size_adapter.view.*

class SizeAdapter (items: Array<String>):
    RecyclerView.Adapter<SizeAdapter.ViewHolder>() {

    val sizes = items

    override fun onBindViewHolder(holder: ViewHolder, position: Int): Unit = with(holder.itemView) {
        val size = sizes[position]

        txtSize.text = size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  ViewHolder(parent.inflate(R.layout.item_size_adapter,false))
    override fun getItemCount() = sizes.size
}