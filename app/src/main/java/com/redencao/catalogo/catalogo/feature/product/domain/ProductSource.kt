package com.redencao.catalogo.catalogo.feature.product.domain

import com.redencao.catalogo.catalogo.feature.database.model.ProductData

interface ProductSource {
    fun addProduct(product: ProductData)
    fun deleteProduct()

    fun getAllProducts()

}