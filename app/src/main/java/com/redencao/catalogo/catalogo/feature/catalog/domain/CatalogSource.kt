package com.redencao.catalogo.catalogo.feature.catalog.domain

import com.redencao.catalogo.catalogo.feature.database.model.ProductData
import io.reactivex.Single

interface CatalogSource {
    fun fetchAll(): Single<List<Product>>
}