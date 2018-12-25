package com.redencao.catalogo.catalogo.feature.product.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.redencao.catalogo.catalogo.R
import com.redencao.catalogo.catalogo.util.extensions.inflate
import kotlinx.android.synthetic.main.item_adapter_image_picked.view.*

class ImagesPickerAdapter constructor(items: List<String>):
    RecyclerView.Adapter<ImagesPickerAdapter.ViewHolder>() {

    val imagesPath = items

    override fun onBindViewHolder(holder: ViewHolder, position: Int): Unit = with(holder.itemView) {
        val imagePath = imagesPath[position]

        Glide.with(context)
            .load(imagePath)
            .into(imgPicked)

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  ViewHolder(parent.inflate(R.layout.item_adapter_image_picked,  false))
    override fun getItemCount() = imagesPath.size
}