package com.redencao.catalogo.catalogo.feature.catalog.repository.mapper

import com.redencao.catalogo.catalogo.feature.catalog.domain.Product
import com.redencao.catalogo.catalogo.feature.database.model.ProductData

object CatalogMapper {
    fun map(productData: List<ProductData>) = productData.map { map(it) }

    fun map(productData: ProductData) = Product(
        id =  productData.id,
        title = productData.title,
        type = productData.type,
        size =  productData.size,
        color = productData.color
    )

}