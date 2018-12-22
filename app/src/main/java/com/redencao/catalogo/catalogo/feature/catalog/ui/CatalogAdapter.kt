package com.redencao.catalogo.catalogo.feature.catalog.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.redencao.catalogo.catalogo.R
import com.redencao.catalogo.catalogo.feature.catalog.domain.Product
import com.redencao.catalogo.catalogo.util.extensions.inflate
import kotlinx.android.synthetic.main.item_adapter_product.view.*

class CatalogAdapter constructor(items: List<Product>):
    RecyclerView.Adapter<CatalogAdapter.ViewHolder>() {

    val products = items

    override fun onBindViewHolder(holder: ViewHolder, position: Int): Unit = with(holder.itemView) {
        val product = products[position]

        txtTitle.text = product.title
        txtType.text = product.type

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  ViewHolder(parent.inflate(R.layout.item_adapter_product,  false))
    override fun getItemCount() = products.size
}