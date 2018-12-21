package com.redencao.catalogo.catalogo.feature.product.repository

import com.redencao.catalogo.catalogo.feature.database.AppDatabase
import com.redencao.catalogo.catalogo.feature.database.model.ProductData
import com.redencao.catalogo.catalogo.feature.product.domain.ProductSource

class ProductRepository(private val appDatabase: AppDatabase): ProductSource {

    override fun addProduct(product: ProductData) {
        appDatabase.productDao().insert(product)
    }

    override fun deleteProduct() { }
    override fun getAllProducts() { }

}