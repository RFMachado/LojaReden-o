package com.redencao.catalogo.catalogo.feature.catalog.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.redencao.catalogo.catalogo.R
import com.redencao.catalogo.catalogo.feature.catalog.domain.Product
import com.redencao.catalogo.catalogo.util.extensions.inflate
import kotlinx.android.synthetic.main.item_adapter_product.view.*

class CatalogAdapter constructor(items: List<Product>, private val listener: (Product) -> Unit):
    RecyclerView.Adapter<CatalogAdapter.ViewHolder>() {

    val products = items

    override fun onBindViewHolder(holder: ViewHolder, position: Int): Unit = with(holder.itemView) {
        val product = products[position]

        txtDescription.text = product.description
        txtCategory.text = product.category

        txtValue.text = context.getString(R.string.product_value, product.value)

        Glide.with(context)
            .load(product.images.firstOrNull())
            .into(imgProduct)

        setOnClickListener {
            listener.invoke(product)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  ViewHolder(parent.inflate(R.layout.item_adapter_product,  false))
    override fun getItemCount() = products.size
}