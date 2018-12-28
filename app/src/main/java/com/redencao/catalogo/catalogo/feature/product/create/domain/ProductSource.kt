package com.redencao.catalogo.catalogo.feature.product.create.domain

import com.redencao.catalogo.catalogo.feature.database.model.ProductData
import io.reactivex.Completable
import io.reactivex.Single

interface ProductSource {
    fun insert(product: ProductData): Completable
    fun delete(product: ProductData): Completable

    fun fetchAll(): Single<List<ProductData>>

}