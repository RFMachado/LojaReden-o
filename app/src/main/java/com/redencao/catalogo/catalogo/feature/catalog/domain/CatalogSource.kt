package com.redencao.catalogo.catalogo.feature.catalog.domain

import io.reactivex.Single

interface CatalogSource {
    fun fetchAll(): Single<List<Product>>
}